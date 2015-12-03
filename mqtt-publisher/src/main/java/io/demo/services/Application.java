package io.demo.services;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 */

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Application - SpringBoot Application Hook
 *
 * @author Donnacha Forde
 * @version Version 0.1 Oct 2015
 * @since X0.0.1
 */
@Component
@PropertySource("classpath:application.properties")
public class Application
{

    //-------------------------------------------------------------------------
    // run

    public void run(String[] args)
    {
    }

}
