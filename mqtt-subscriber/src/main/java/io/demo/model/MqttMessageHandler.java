package io.demo.model;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

/**
 * MqttMessageHandler - Separate, dedicated class implementation for MQTT event callbacks.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public class MqttMessageHandler implements MqttCallback
{

    //-------------------------------------------------------------------------
    // construction

    public MqttMessageHandler()
    {
    }


    /**
     * Factory method - create an instance of an alert.
     *
     * @return - new instance.
     *
     */
    public static MqttMessageHandler createInstance()
    {
        return new MqttMessageHandler();
    }



    //-------------------------------------------------------------------------
    // interface implementation callbacks


    @Override
    public void connectionLost(Throwable throwable)
    {
        System.out.println("ERROR: Connection to Message Broker has been lost.");
    }


    @Override
    public void messageArrived(String strMqttTopic, MqttMessage mqttMessage) throws Exception
    {
        // convert the array of bytes to a string
        String strMessage = new String(mqttMessage.getPayload(), "UTF-8");

        // show message contents
        Date now = new Date();
        System.out.println(now + " [Event] " +  strMessage);
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken mqttDeliveryToken)
    {
        // this only gets invoked when you specify the appropriate QOS
        System.out.println("INFO: Received confirmation of message delivery.");
    }
}
