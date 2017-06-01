# virtual-topics

This Spring Boot Camel example demonstrates the use of Virtual Topics in ActiveMQ.

Virtual Topics are used as a way of allowing multiple load-balancing, competing consumers to subscribe to the same topic. This would not normally be possible under the JMS specification, where a subscription to a topic is for a single subscriber only.

With Virtual Topics, however, messages published to a topic can be distributed to one or more queues, according to configuration. Since a feature of queues is the ability to have multiple consumers, this then makes load balancing and failover of consumers possible.

To run this example:

    mvn spring-boot:run

The application will start an embedded broker on `tcp://localhost:61616`. Two consumers will be created, which receive messages published to the `VirtualTopic.orders` topic. Each test message published to the topic is then consumed by either consumer A or consumer B.

This application starts an embedded ActiveMQ broker, which is defined using Spring XML configuration in `resources/activemq-brokers.xml`.
