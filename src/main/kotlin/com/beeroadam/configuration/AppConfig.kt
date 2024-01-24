package com.beeroadam.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 * AppConfig is a configuration class that sets up a Spring MVC application.
 * It is annotated with @Configuration, @EnableWebMvc, and @ComponentScan.
 *
 * @Configuration indicates that this class contains bean definitions for the application context.
 *
 * @EnableWebMvc is used to enable Spring's MVC (Model-View-Controller) capabilities.
 * This annotation configures a variety of beans that are necessary for Spring MVC applications.
 *
 * @ComponentScan tells Spring to scan the specified package(s) for components, configurations, and services.
 * In this case, it's scanning the "com.beeroadam" package.
 *
 * @author abiro
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["com.beeroadam"])
class AppConfig