# wildcard-consumers

This Spring Boot Camel example demonstrates the use of Wildcard Consumers in ActiveMQ 5._x_.

Wildcards allow a single consumer to be able to receive messages from a range of destinations. Using the ActiveMQ `>` and `*` wildcard symbols, a single consumer can receive messages from multiple queues or topics.

To run this example:

    mvn spring-boot:run

The application will start an embedded broker on `tcp://localhost:61616`. Two consumers are created, which are configured to receive messages on the topics `stock.NASDAQ.>` and `stock.LON.>`. This means that they will receive all messages sent to topic names that begin `stock.NASDAQ.` or `stock.LON.`.

The application then uses a timer to send a test message to each topic in turn (in round-robin fashion). Each consumer writes a log when it receives a message, showing the stock price and the topic that the message was sent to (by referencing the `JMSDestination` header).

