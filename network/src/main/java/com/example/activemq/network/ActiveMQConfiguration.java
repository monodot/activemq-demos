package com.example.activemq.network;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Creates the ActiveMQ components in Camel.
 */
@Configuration
@ImportResource("classpath:activemq-brokers.xml")
public class ActiveMQConfiguration {

    @Bean(name="amqbroker1")
    public ActiveMQComponent createActiveMQComponentBroker1() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61616,tcp://localhost:61617,tcp://localhost:61618)");
        component.setBrokerURL("tcp://localhost:61616");
        return component;
    }

    @Bean(name="amqbroker2")
    public ActiveMQComponent createActiveMQComponentBroker2() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61617,tcp://localhost:61618,tcp://localhost:61616)");
        component.setBrokerURL("tcp://localhost:61617");
        return component;
    }

    @Bean(name="amqbroker3")
    public ActiveMQComponent createActiveMQComponentBroker3() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61618,tcp://localhost:61616,tcp://localhost:61617)");
        component.setBrokerURL("tcp://localhost:61618");
        return component;
    }

}
