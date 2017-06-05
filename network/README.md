# network

This Spring Boot Camel example demonstrates a [Network of Brokers][2] in ActiveMQ 5._x_.

A Network of Brokers is a way of deploying ActiveMQ that allows queues and topics to be distributed over several brokers. This can be used to implement load balancing and resilience. When a network of brokers is formed, clients can simply connect to another broker in the event of a failure of a single broker.

To run this example:

    mvn clean spring-boot:run

Three embedded ActiveMQ brokers are started, running on ports 61616-61618. Each has its own persistent data store (kahaDB), which is created under the `target/` directory on startup:

    <amq:kahaDB directory="target/data/brokerN" journalMaxFileLength="32mb"/>

On startup, each broker discovers others using multicast, and a network is formed - a _complete graph_ topology (where every broker is connected to every other broker in the network). This is configured in each broker's XML configuration file like so:

    <amq:transportConnector name="broker1tcp" uri="tcp://localhost:61616" discoveryUri="multicast://default" />

    ....

    <amq:networkConnector name="broker1connector" uri="multicast://default"/>

In the application, a new message is pushed every 5 seconds into queue `FOO` on broker A. Two discrete consumers are connected to the same queue `FOO`, on broker B and C respectively. Each message is then consumed exactly once, either by the consumer connected to broker B, or by the consumer connected to broker C.

This demonstrates a message "moving" from broker A, over to broker B or C in the network, when a consumer is connected to those brokers.

ActiveMQ attempts to perform basic load balancing between brokers, so that messages are evenly distributed between broker B and C if consumers are connected on each. (Note that this behaviour becomes more complex when multiple consumers are connected on a broker to the same queue - see the [documentation for the `conduitSubscription` setting][2]).

Note that _mvn clean_ will delete the brokers' message stores.

(Optional) To visually inspect the running brokers:

- Install the [Hawtio extension for Google Chrome][1]
- In Chrome, open a new tab, go to Apps > Hawtio
- In Hawtio, connect to `localhost`, port `8080`, path `jolokia`.


[1]: https://chrome.google.com/webstore/detail/hawtio/aemcedanjggpkpeghpmlmioopekhhppl
[2]: http://activemq.apache.org/networks-of-brokers.html

