package com.example.activemq.network;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Sends a message every 5 seconds
        from("timer:foo?period=5000")
                .setBody(simple("${random(1000,2000)}"))
                .log(">> Sending stock quote to broker1 - ${body}")
                .to("amqbroker1:queue:FOO");

        from("amqbroker2:queue:FOO")
                .log(">> Received message from broker2 - ${body}");

        from("amqbroker3:queue:FOO")
                .log(">> Received message from broker3 - ${body}");

    }
}
