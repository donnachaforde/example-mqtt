package io.demo.model;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */


import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

/**
 * MqttSubscriber - Concrete implementation of wrapper class that reflects MQTT Subscriber lifecycle points.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public class MqttSubscriber implements IMqttSubscriber
{
    //-------------------------------------------------------------------------
    // member vars

    // handle to our MQTT client subscriber
    private MqttClient mqttSubscriber;



    //-------------------------------------------------------------------------
    // construction

    public MqttSubscriber()
    {
    }


    /**
     * Factory method - create an instance of an alert.
     *
     * @return - new instance.
     *
     */
    public static MqttSubscriber createInstance()
    {
        return new MqttSubscriber();
    }


    //-------------------------------------------------------------------------
    // initialization

    /**
     * Initialize a connection with the given MQTT Broker
     *
     * @param strMqttMessageBroker MQTT Message Broker host details - e.g. "tcp://localhost:1883"
     * @param mqttCallback ref to concrete instance of callback message handler
     */
    public void initialize(final String strMqttMessageBroker, MqttCallback mqttCallback)
    {
        /*
         * Create our MQTT subscriber
         */

        System.out.println("Establishing connection with the MQTT Broker=" + strMqttMessageBroker + "...");

        // each MQTT client needs a unique identifier
        String strSubscriberId = UUID.randomUUID().toString();

        MemoryPersistence memoryPersistence = new MemoryPersistence();
        try
        {
            mqttSubscriber = new MqttClient(strMqttMessageBroker, strSubscriberId, memoryPersistence);
        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
        }


        /*
         * Register our callback hook
         */

        mqttSubscriber.setCallback(mqttCallback);


        /*
         * Connect our MQTT subscriber
         */

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);

        try
        {
            mqttSubscriber.connect(mqttConnectOptions);
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Connected MQTT Subscriber as ClientID=" + strSubscriberId.toString() + ".");
    }

    /**
     * Subscribe to listen for messages on the given MQTT Topic.
     *
     * @param strMqttTopic MQTT topic name
     */
    @Override
    public void subscribe(String strMqttTopic)
    {
        System.out.println("Subscribing on MQTT Topic='" + strMqttTopic + "'...");

        try
        {
            mqttSubscriber.subscribe(strMqttTopic);
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Listening on MQTT Topic='" + strMqttTopic + "'.");
    }

    /**
     * Disconnect from the MQTT Broker.
     */
    @Override
    public void disconnect()
    {
        System.out.println("Disconnecting MQTT Subscriber...");

        try
        {
            mqttSubscriber.disconnect();
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Disconnected MQTT Subscriber.");
    }
}
