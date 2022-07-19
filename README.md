# example-mqtt
Separate Java examples of MQTT Publisher & Subscriber.

(Examples I encountered when learning to work with MQTT combined both the sender and the receiver logic. Here, I've separated them out.)


## Requirements

* JDK 8 
* Maven
* An MQTT Broker (As a suggestion, check out http://www.hivemq.com as a decent option for the demo here. )


## Build & Deployment Instructions

1. Clone the repo and build the projects. 
2. Install your MQTT Broker.
3. Run the MQTT Subscriber in its own console/shell.
4. Run the MQTT Publisher in its own console/shell. 

**Note:** You'll need to start the `publisher` quickly as the subscriber will only wait to listen for events for 15 secs by default.

Audit/logging output is written to `stdout` so you should see some send/receive messages in both consoles. 