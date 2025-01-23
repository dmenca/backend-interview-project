package com.dmenca.java.basic.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="kafka.realtime")
@Configuration
public class RealtimeConfigProperties extends CommonConfigProperties{
}
