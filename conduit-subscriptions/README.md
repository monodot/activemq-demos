# conduit-subscriptions

This example, developed using Spring Boot and Camel, demonstrates [Conduit Subscriptions][1] in ActiveMQ 5._x_.

Conduit subscriptions are a way of consolidating multiple subscriptions to the same queue or topic into a single subscription. In a network of brokers, this changes the way that messages are distributed to consumers.

- For queues, when conduit subscriptions are enabled, messages will be distributed evenly between brokers which have consumers.
- For topics, conduit subscriptions need to be enabled to avoid duplication of topic messages.

To run this example:

    mvn clean spring-boot:run

This will start three brokers, connected as a network of brokers, in complete graph topology. The default mode is to set `conduitSubscriptions="true"` on the network bridge.

This will ensure that multiple consumers subscribing to the same destination are treated as one consumer by the network. The result is that messages are distributed evenly across **brokers**.

This extract from the log file shows what happens:

    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1996
    INFO 33335 --- [msConsumer[FOO]] route5      : >> Received message from broker3 - Consumer C - 1996
    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1766
    INFO 33335 --- [msConsumer[FOO]] route2      : >> Received message from broker2 - 1766
    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1072
    INFO 33335 --- [msConsumer[FOO]] route4      : >> Received message from broker3 - Consumer B - 1072
    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1549
    INFO 33335 --- [msConsumer[FOO]] route2      : >> Received message from broker2 - 1549
    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1176
    INFO 33335 --- [msConsumer[FOO]] route3      : >> Received message from broker3 - Consumer A - 1176
    INFO 33335 --- [4 - timer://foo] route1      : >> Sending stock quote - 1573
    INFO 33335 --- [msConsumer[FOO]] route2      : >> Received message from broker2 - 1573

See how messages are distributed evenly between brokers (2 and 3); and then within a broker the messages are distributed evenly between each consumer (A, B, C).

---

Now run the example using:

    mvn clean spring-boot:run -Dconduit.subscriptions=false

This will set `conduitSubscription="false"` on the network bridge. This means that a proxy consumer will be created for every queue consumer. This is the opposite of the demo above, where all consumers on a queue on a single broker are consolidated into one subscription. 

The result is that messages are distributed evenly across all **consumers**. Note: this does not mean that the load is balanced all **brokers**. 

This extract from the log file shows messages being distributed to each consumer in round-robin fashion:

    INFO 32649 --- [4 - timer://foo] route1      : >> Sending stock quote - 1887
    INFO 32649 --- [msConsumer[FOO]] route2      : >> Received message from broker2 - 1887
    INFO 32649 --- [4 - timer://foo] route1      : >> Sending stock quote - 1311
    INFO 32649 --- [msConsumer[FOO]] route4      : >> Received message from broker3 - Consumer B - 1311
    INFO 32649 --- [4 - timer://foo] route1      : >> Sending stock quote - 1821
    INFO 32649 --- [msConsumer[FOO]] route5      : >> Received message from broker3 - Consumer C - 1821
    INFO 32649 --- [4 - timer://foo] route1      : >> Sending stock quote - 1821
    INFO 32649 --- [msConsumer[FOO]] route3      : >> Received message from broker3 - Consumer A - 1821

[1]: http://activemq.apache.org/networks-of-brokers.html

