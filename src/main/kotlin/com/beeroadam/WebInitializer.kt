package com.beeroadam

import com.beeroadam.configuration.AppConfig
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class WebInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<Class<*>> {
        return arrayOf(AppConfig::class.java)
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }
}