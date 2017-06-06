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
                .log(">> Sending stock quote - ${body}")
                .to("amqbroker1:queue:FOO");

        from("amqbroker2:queue:FOO")
                .log(">> Received message from broker2 - ${body}");

        from("amqbroker3a:queue:FOO")
                .log(">> Received message from broker3 - Consumer A - ${body}");
        from("amqbroker3b:queue:FOO")
                .log(">> Received message from broker3 - Consumer B - ${body}");
        from("amqbroker3c:queue:FOO")
                .log(">> Received message from broker3 - Consumer C - ${body}");

    }
}
