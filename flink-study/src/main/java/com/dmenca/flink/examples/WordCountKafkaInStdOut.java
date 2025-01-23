package com.dmenca.flink.examples;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.HeartbeatManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;

import java.util.Optional;
import java.util.Properties;

public class WordCountKafkaInStdOut {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setInteger(RestOptions.PORT, 28081);
        configuration.setString("metrics.reporters", "prom");
        configuration.setString("metrics.reporter.prom.factory.class", "org.apache.flink.metrics.prometheus.PrometheusReporterFactory");
        configuration.setString("metrics.reporter.prom.port", "9020-9040");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","10.111.32.127:9092");
        properties.setProperty("group.id","flink-group");
        properties.setProperty("security.protocol", "SASL_PLAINTEXT"); // 或 "SASL_SSL"，具体取决于您的配置
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                        "username=\"admin\" password=\"Ctem5azbtJ2eF8c\";");

        // 设置kafka数据来源
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
                .setProperties(properties)
                .setTopics("Shakepeare")
                .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.EARLIEST))
                // 需要设置反序列化
                .setValueOnlyDeserializer(new SimpleStringSchema()) // 设置反序列化模式
                .build();

        WatermarkStrategy<String> watermarkStrategy = WatermarkStrategy.noWatermarks();

        // 通过kafka来源创建DataStreamSource
        DataStreamSource<String> kafkaDataStreamSource = env.fromSource(kafkaSource, watermarkStrategy, "kafkaSource");
        kafkaDataStreamSource.print("kafka data source");
        DataStream<Tuple2<String, Integer>> sumDataStream = kafkaDataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        String[] tokens = line.split("\\s");
                        for (String token : tokens) {
                            if (token.length() > 0) {
                                collector.collect(new Tuple2<>(token, 1));
                            }
                        }
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(tuple -> tuple.f0)
                // 使用timewindow则不进入统计
                // 使用processTime的时间窗口
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .sum(1);

        sumDataStream.print("sum data stream");

        env.execute("kafka streaming word count");
    }
}
