package com.example.activemq.virtualtopics;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * Created by tdonohue on 01/06/2017.
 */
@Component
public class MyCamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=5000")
                .setBody(simple("Hello, world!"))
                .log(">> Sending message to topic: ${body}")
                .to("amqProducer:topic:VirtualTopic.orders");

        from("amqConsumerA:queue:Consumer.MyConsumer.VirtualTopic.orders")
                .log(">> Consumer A received message from orders: ${body}");

        from("amqConsumerB:queue:Consumer.MyConsumer.VirtualTopic.orders")
                .log(">> Consumer B received message from orders: ${body}");

    }
}
