package com.beeroadam.configuration

import io.prometheus.client.CollectorRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * PrometheusConfiguration is a configuration class for setting up Prometheus, a system monitoring and alerting toolkit.
 *
 * @Configuration indicates that this class declares one or more @Bean methods.
 *
 * @author abiro
 */
@Configuration
class PrometheusConfiguration {

    /**
     * This method returns the default CollectorRegistry instance.
     * The CollectorRegistry is a part of the Prometheus Java client library, and it is responsible for registering and collecting metrics data.
     *
     * @return the default CollectorRegistry instance.
     */
    @Bean
    fun collectorRegistry(): CollectorRegistry {
        return CollectorRegistry.defaultRegistry
    }
}