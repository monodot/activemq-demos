package com.example.activemq.network;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Creates the ActiveMQ components in Camel.
 */
@Configuration
@ImportResource("classpath:activemq-brokers-conduit-${conduit.subscriptions}.xml")
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

    @Bean(name="amqbroker3a")
    public ActiveMQComponent createActiveMQComponentBroker3a() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61618,tcp://localhost:61616,tcp://localhost:61617)");
        component.setBrokerURL("tcp://localhost:61618");
        return component;
    }

    @Bean(name="amqbroker3b")
    public ActiveMQComponent createActiveMQComponentBroker3b() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61618,tcp://localhost:61616,tcp://localhost:61617)");
        component.setBrokerURL("tcp://localhost:61618");
        return component;
    }

    @Bean(name="amqbroker3c")
    public ActiveMQComponent createActiveMQComponentBroker3c() {
        ActiveMQComponent component = new ActiveMQComponent();
        //component.setBrokerURL("failover:(tcp://localhost:61618,tcp://localhost:61616,tcp://localhost:61617)");
        component.setBrokerURL("tcp://localhost:61618");
        return component;
    }

}
