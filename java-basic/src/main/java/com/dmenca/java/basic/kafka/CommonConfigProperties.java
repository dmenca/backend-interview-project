package com.dmenca.java.basic.kafka;

import lombok.Data;
import org.apache.kafka.common.security.plain.internals.PlainSaslServer;

import java.io.Serializable;
@Data
public class CommonConfigProperties {

    private String brokerAddress;

    private String topic;

    private KafkaSaslAuth kafkaSaslAuth;

//    private String userName;
//
//    private String password;

    public boolean authEnable() {
        return null != kafkaSaslAuth ? kafkaSaslAuth.enable : false;
    }

    @Data
    public static class KafkaSaslAuth implements Serializable {

        /**
         * kafka sasl enable
         */
        private boolean enable;


        /**
         * sasl protocol
         */
        private String protocol;

        /**
         * sasl mechanism
         */
        private String mechanism;

        /**
         * auth username
         */
        private String saslUsername;

        /**
         * auth password
         */
        private String saslPassword;

        public String getMechanism() {
            return null != mechanism ? mechanism : PlainSaslServer.PLAIN_MECHANISM;
        }

        public String getProtocol() {
            return null != protocol ? protocol : "SASL_PLAINTEXT";
        }

        public String getSaslJaasConfig() {
            String config = "org.apache.kafka.common.security.plain.PlainLoginModule " +
                    "required username=\"%s\" password=\"%s\";";
            return String.format(config, this.saslUsername, this.saslPassword);
        }
    }
}
