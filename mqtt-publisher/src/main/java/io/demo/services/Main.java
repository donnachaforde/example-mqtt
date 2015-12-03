package io.demo.services;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */


import io.demo.model.IMqttPublisher;
import io.demo.model.MqttPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Main - Plain Ol' main statement for normal java app.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Dec 2015
 * @since X0.0.1
 */

@PropertySource("classpath:application.properties")
public class Main
{
    //-------------------------------------------------------------------------
    // member vars

    // the MQTT Broker
    @Value("${mqtt.broker.host}")
    public static String mqttBroker = "tcp://localhost:1883";

    // the MQTT topic name
    @Value("${mqtt.topic.name}")
    public static String mqttTopic = "my-mqtt-topic-name";

    // message string to publish
    @Value("${message.publish.content}")
    public static String strMessage = "Message from our MQTT Publisher";

    // the MQTT topic name
    @Value("${message.publish.count}")
    public static long numSends = 5;

    // the MQTT topic name
    @Value("${message.publish.delayInMillisecs}")
    public static long delayPeriod = 1000;


    /* *************************************************************************** *\
       ***************************************************************************
       ***************************************************************************
       ******************************    MAIN    *********************************
       ***************************************************************************
    \* *************************************************************************** */

    public static void main(String[] args)
    {
        /*
         * Initialize our MQTT Publisher
         */

        IMqttPublisher mqttPublisher = MqttPublisher.createInstance();
        mqttPublisher.initialize(mqttBroker);


        /*
         * Send a few messages
         */

        for (int i = 0; i < numSends; i++)
        {
            mqttPublisher.publish(mqttTopic, strMessage);

            // pause briefly before sending next message
            try
            {
                Thread.currentThread().sleep(delayPeriod);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }


        /*
         * And disconnect
         */

        mqttPublisher.disconnect();;

    }
}
