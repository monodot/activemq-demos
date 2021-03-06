package com.example.activemq.virtualtopics;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by tdonohue on 01/06/2017.
 */
@Configuration
@ImportResource("classpath:activemq-brokers.xml")
public class ActiveMQConfiguration {

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
