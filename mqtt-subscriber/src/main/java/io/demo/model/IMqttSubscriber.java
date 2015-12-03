package io.demo.model;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */

import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * IMqttSubscriber - Interface definition for an MQTT Subscriber, illustrating lifecyle points.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public interface IMqttSubscriber
{
    /**
     * Initialize a connection with the given MQTT Broker
     *
     * @param strMqttMessageBroker MQTT Message Broker host details - e.g. "tcp://localhost:1883"
     * @param mqttCallback ref to concrete instance of callback message handler
     */
    public void initialize(final String strMqttMessageBroker, MqttCallback mqttCallback);

    /**
     * Subscribe to listen for messages on the given MQTT Topic.
     *
     * @param strMqttTopic MQTT topic name
     */
    public void subscribe(final String strMqttTopic);

    /**
     * Disconnect from the MQTT Broker.
     */
    public void disconnect();

}
