package io.demo.model;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */

/**
 * IMqttPublisher -  - Interface definition for an MQTT Subscriber, illustrating lifecyle points.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public interface IMqttPublisher
{
    /**
     * Initialize a connection with the given MQTT Broker
     *
     * @param strMqttMessageBroker MQTT Message Broker host details - e.g. "tcp://localhost:1883"
     */
    public void initialize(final String strMqttMessageBroker);

    /**
     * Publish a message on the given topic to the MQTT Broker
     *
     * @param strMqttTopic MQTT Topic to publish to
     * @param strMessage Message to publish
     */
    public void publish(final String strMqttTopic, final String strMessage);

    /**
     * Disconnect from the MQTT Broker.
     */
    public void disconnect();


}
