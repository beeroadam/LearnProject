package com.beeroadam

import com.beeroadam.configuration.AppConfig
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 * This class initializes the web application. It extends AbstractAnnotationConfigDispatcherServletInitializer
 * to provide configuration for the DispatcherServlet context.
 */
class WebInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    /**
     * This method is used to specify the configuration classes for the root application context.
     * The root context is shared by all servlets and filters.
     *
     * @return Array<Class<*>> - an array of configuration classes for the root application context.
     */
    override fun getRootConfigClasses(): Array<Class<*>> {
        return arrayOf(AppConfig::class.java)
    }

    /**
     * This method is used to specify the configuration classes for the DispatcherServlet application context.
     * The returned classes are used to configure the context for the DispatcherServlet.
     *
     * @return Array<Class<*>>? - an array of configuration classes for the DispatcherServlet application context.
     *                             Returns null as there are no configuration classes for the DispatcherServlet.
     */
    override fun getServletConfigClasses(): Array<Class<*>>? {
        return null
    }

    /**
     * This method is used to specify the servlet mappings for the DispatcherServlet.
     * The returned strings are used as URL patterns that the DispatcherServlet will be mapped to.
     *
     * @return Array<String> - an array of servlet mappings for the DispatcherServlet.
     */
    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }
}