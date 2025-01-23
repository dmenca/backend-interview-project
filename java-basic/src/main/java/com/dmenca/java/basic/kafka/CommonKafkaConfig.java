package com.dmenca.java.basic.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenhongxing
 */
public class CommonKafkaConfig {

    /**
     * 生产者配置参数
     * @return
     */
    public ProducerFactory<String, String> producerFactory(CommonConfigProperties properties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBrokerAddress());
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 20971520);
        if (properties.authEnable()) {
            CommonConfigProperties.KafkaSaslAuth kafkaSaslAuth = properties.getKafkaSaslAuth();
            props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaSaslAuth.getSaslJaasConfig());
            props.put(SaslConfigs.SASL_MECHANISM, kafkaSaslAuth.getMechanism());
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaSaslAuth.getProtocol());
        }
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * 消费者配置参数
     * @return
     */
    public Map<String, Object> consumerProperties(CommonConfigProperties properties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBrokerAddress());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "s1pGroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        if (properties.authEnable()) {
            CommonConfigProperties.KafkaSaslAuth kafkaSaslAuth = properties.getKafkaSaslAuth();
            props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaSaslAuth.getSaslJaasConfig());
            props.put(SaslConfigs.SASL_MECHANISM, kafkaSaslAuth.getMechanism());
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaSaslAuth.getProtocol());
        }

        return props;
    }

}
