package com.beeroadam.configuration

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * WebConfig is a configuration class for the web layer of a Spring Boot application.
 *
 * @Configuration indicates that this class declares one or more @Bean methods.
 *
 * The class implements the WebMvcConfigurer interface, which provides callback methods to customize the Java-based configuration for Spring MVC.
 *
 * @author abiro
 */
@Configuration
class WebConfig: WebMvcConfigurer {

    /**
     * This method is an override from the WebMvcConfigurer interface and is used to add or modify HttpMessageConverters in the application.
     * It iterates over the list of converters and if it finds an instance of MappingJackson2HttpMessageConverter, it modifies its ObjectMapper to register the JavaTimeModule and disable the WRITE_DATES_AS_TIMESTAMPS feature.
     *
     * @param converters List of HttpMessageConverter to be extended or modified.
     */
    override fun extendMessageConverters(converters: List<HttpMessageConverter<*>?>) {
        for (converter in converters) {
            if (converter is MappingJackson2HttpMessageConverter) {
                val mapper = converter.objectMapper
                mapper.registerModule(JavaTimeModule())
                mapper.registerModule(
                    KotlinModule.Builder().build()
                )
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            }
        }
    }
}