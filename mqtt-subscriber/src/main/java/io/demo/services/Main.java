package io.demo.services;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */


import io.demo.model.IMqttSubscriber;
import io.demo.model.MqttMessageHandler;
import io.demo.model.MqttSubscriber;
import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * Main - Plain Ol' main statement with crude keep-alive.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */
public class Main
{
    //-------------------------------------------------------------------------
    // member vars

    // the MQTT Broker
    public static String mqttBroker = "tcp://localhost:1883";

    // the MQTT topic name
    public static String mqttTopic = "my-mqtt-topic-name";

    // message string to publish
    public static String strMessage = "Message from our MQTT Publisher";

    // the MQTT topic name
    public static long numSends = 5;

    // the MQTT topic name
    public static long delayPeriod = 1000 * 15;



    /* *************************************************************************** *\
       ***************************************************************************
       ***************************************************************************
       ******************************    MAIN    *********************************
       ***************************************************************************
    \* *************************************************************************** */

    public static void main(String[] args)
    {
        /*
         * Initialize our MQTT Subscriber
         */

        // here we're using a separate class as the message callback handler
        MqttCallback mqttCallback = MqttMessageHandler.createInstance();

        IMqttSubscriber mqttSubscriber = MqttSubscriber.createInstance();
        mqttSubscriber.initialize(mqttBroker, mqttCallback);


        /*
         * Start listening on the relevant topic
         */

        mqttSubscriber.subscribe(mqttTopic);


        /*
         * Crude method to keep this thread alive (and hence, the process) to given listening thread
         * a chance to process some events.
         *
         */

        long delayPeriodInSecs = (delayPeriod / 1000);
        System.out.println("Waiting for " + delayPeriodInSecs + " secs to receive messages...");

        try
        {
            Thread.currentThread().sleep(delayPeriod);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Completed waiting for messages.");


        /*
         * Finally, disconnect
         */

        mqttSubscriber.disconnect();;

    }
}
