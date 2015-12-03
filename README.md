# example-mqtt
Separate Java examples of MQTT Publisher & Subscriber.

(Examples I encountered when learning to work with MQTT combined both the sender and the receiver logic. Here, I've separated them out.)


## Requirements

1. JDK 8 & Maven
3. An MQTT Broker (Check out http://www.hivemq.com)


## Build & Deployment Instructions

1. Clone the repo and build the projects. 
2. Install your MQTT Broker.
3. Run the MQTT Subscriber in its own console/shell.
4. Run the MQTT Publisher in its own console/shell. (You'll need to be quick - the subscriber only hangs around to listen for events for 15 secs by default.)

Output is written to stdout so you should see some send/receive messages in both consoles/shells. 