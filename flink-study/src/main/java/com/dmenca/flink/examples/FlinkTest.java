package com.dmenca.flink.examples;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkTest {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        // 简单的打印示例
        env.fromElements("Hello", "Flink")
                .map(String::toUpperCase)
                .print();

        // 提交作业
        env.execute("Test Job");
    }
}
