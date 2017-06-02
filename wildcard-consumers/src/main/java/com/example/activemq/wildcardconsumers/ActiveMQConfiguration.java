package com.example.activemq.wildcardconsumers;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Creates a broker, and the ActiveMQ producer and consumer components in Camel.
 * The ActiveMQComponents configure connection pooling automatically.
 */
@Configuration
@ImportResource("classpath:activemq-brokers.xml")
public class ActiveMQConfiguration {

    @Bean(name="amq")
    public ActiveMQComponent createActiveMQComponent() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setBrokerURL("tcp://localhost:61616");
        return component;
    }


    @Bean(name="amqProducer")
    public ActiveMQComponent createActiveMQComponentProducer() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setBrokerURL("tcp://localhost:61616");
        return component;
    }

    @Bean(name="amqConsumerA")
    public ActiveMQComponent createActiveMQComponentConsumerA() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setBrokerURL("tcp://localhost:61616");
        return component;
    }

    @Bean(name="amqConsumerB")
    public ActiveMQComponent createActiveMQComponentConsumerB() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setBrokerURL("tcp://localhost:61616");
        return component;
    }



}
