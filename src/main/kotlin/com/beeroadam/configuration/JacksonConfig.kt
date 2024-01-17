package com.beeroadam.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfig {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        val objectMapper = Jackson2ObjectMapperBuilder().createXmlMapper(false).build<ObjectMapper>()
        objectMapper.findAndRegisterModules()
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return objectMapper
    }
}