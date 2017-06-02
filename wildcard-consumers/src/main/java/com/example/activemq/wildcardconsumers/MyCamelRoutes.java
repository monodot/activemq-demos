package com.example.activemq.wildcardconsumers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Sends a stock quote every 5 seconds to a different topic
        from("timer:foo?period=5000")
                .setBody(simple("${random(1000,2000)}"))
                .log(">> Sending stock quote: ${body}")
                .loadBalance()
                    .roundRobin()
                    .to("amq:topic:stock.NASDAQ.MSFT",
                            "amq:topic:stock.NASDAQ.AAPL",
                            "amq:topic:stock.LON.BARC",
                            "amq:topic:stock.LON.HSBA");

        // This consumer receives messages on all NASDAQ.> topics
        from("amq:topic:stock.NASDAQ.>")
                .log(">> NASDAQ consumer received price: ${body} on ${headers.JMSDestination}");

        // This consumer receives messages on all LON.> topics
        from("amq:topic:stock.LON.>")
                .log(">> LON consumer received price: ${body} on ${headers.JMSDestination}");

    }
}
