package com.beeroadam.configuration

import io.prometheus.client.CollectorRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PrometheusConfiguration {

    @Bean
    fun collectorRegistry(): CollectorRegistry {
        return CollectorRegistry.defaultRegistry
    }
}