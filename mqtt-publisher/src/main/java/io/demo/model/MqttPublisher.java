package io.demo.model;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

/**
 * MqttPublisher - <desc>
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public class MqttPublisher implements IMqttPublisher
{
    // ----------------------------------------------------------------------------
    // meta data

    // shorthand ref to our class name
    private static final String className = MqttPublisher.class.getSimpleName();


    //-------------------------------------------------------------------------
    // member vars

    // handle to our MQTT client publisher
    protected MqttClient mqttPublisher = null;



    //-------------------------------------------------------------------------
    // construction

    /**
     * Default constructor - required for serialization purposes.
     *
     * Note: This ctor is not intended to be used directly.
     */
    public MqttPublisher()
    {
    }

    /**
     * Factory method - create an instance of an alert.
     *
     * @return - new instance.
     *
     */
    public static MqttPublisher createInstance()
    {
        return new MqttPublisher();
    }


    //-------------------------------------------------------------------------
    // interface implementations

    /**
     * Initialize a connection with the given MQTT Broker
     *
     * @param strMqttMessageBroker MQTT Message Broker host details - e.g. "tcp://localhost:1883"
     */
    public void initialize(final String strMqttMessageBroker)
    {
        /*
         * Create our MQTT subscriber
         */

        System.out.println("Establishing connection with the MQTT Broker=" + strMqttMessageBroker + "...");

        // each MQTT client needs a unique identifier
        String strMqttPublisherId = UUID.randomUUID().toString();

        MemoryPersistence memoryPersistence = new MemoryPersistence();
        try
        {
            mqttPublisher = new MqttClient(strMqttMessageBroker, strMqttPublisherId, memoryPersistence);
        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
        }


        /*
         * Connect our MQTT publisher
         */

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);

        try
        {
            mqttPublisher.connect(mqttConnectOptions);
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Connected MQTT Publisher as ClientID=" + strMqttPublisherId + ".");
    }

    /**
     * Publish a message on the given topic to the MQTT Broker
     *
     * @param strMqttTopic MQTT Topic to publish to
     * @param strMessage Message to publish
     */
    public void publish(final String strMqttTopic, final String strMessage)
    {
        System.out.println("Publishing message='" + strMessage + "' on topic='" + strMqttTopic + "'...");

        int QOS = 2;
        try
        {
            MqttMessage mqttMessage = new MqttMessage(strMessage.getBytes());
            //mqttMessage.setQos(QOS);
            mqttPublisher.publish(strMqttTopic, mqttMessage);
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Published message.");

    }

    /**
     * Terminate the MQTT client connection.
     *
     */
    public void disconnect()
    {
        System.out.println("Disconnecting MQTT Publisher...");

        try
        {
            this.mqttPublisher.disconnect();
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        System.out.println("Disconnected MQTT Publisher.");
    }


}
