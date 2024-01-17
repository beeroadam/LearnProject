package com.beeroadam.controller

import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/metrics")
class PrometheusMetricsController(private val collectorRegistry: CollectorRegistry) {

    @GetMapping(produces = [TextFormat.CONTENT_TYPE_004])
    fun metrics(response: HttpServletResponse) {
        TextFormat.write004(response.writer, collectorRegistry.metricFamilySamples())
    }
}