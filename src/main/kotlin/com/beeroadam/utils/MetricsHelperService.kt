package com.beeroadam.utils

import io.prometheus.client.CollectorRegistry
import io.prometheus.client.Counter
import org.springframework.stereotype.Service

@Service
class MetricsHelperService(collectorRegistry: CollectorRegistry) {
    companion object {
        const val PRODUCT_DETAILS_SUCCESS = "product_details_successful_requests_total"
        const val PRODUCT_DETAILS_FAIL = "product_detail_failed_requests_total"
        const val PRODUCTS_LIST_SUCCESS = "products_list_successful_requests_total"
        const val PRODUCTS_LIST_FAIL = "products_list_failed_requests_total"
        const val PRODUCT_CREATE_SUCCESS = "product_create_successful_requests_total"
        const val PRODUCT_CREATE_FAIL = "product_create_failed_requests_total"
        const val PRODUCT_DELETE_SUCCESS = "product_delete_successful_requests_total"
        const val PRODUCT_DELETE_FAIL = "product_delete_failed_requests_total"
        const val PRODUCT_UPDATE_SUCCESS = "product_update_successful_requests_total"
        const val PRODUCT_UPDATE_FAIL = "product_update_failed_requests_total"

        const val USER_LIST_SUCCESS = "user_list_successful_requests_total"
        const val USER_LIST_FAIL = "user_list_failed_requests_total"
        const val USER_DETAIL_SUCCESS = "user_detail_successful_requests_total"
        const val USER_DETAIL_FAIL = "user_detail_failed_requests_total"
        const val USER_CREATE_SUCCESS = "user_create_successful_requests_total"
        const val USER_CREATE_FAIL = "user_create_failed_requests_total"
        const val USER_DELETE_SUCCESS = "user_delete_successful_requests_total"
        const val USER_DELETE_FAIL = "user_delete_failed_requests_total"
        const val USER_UPDATE_SUCCESS = "user_update_successful_requests_total"
        const val USER_UPDATE_FAIL = "user_update_failed_requests_total"
    }

    private val counters = mapOf(
        PRODUCT_DETAILS_SUCCESS to Counter.build().name(PRODUCT_DETAILS_SUCCESS)
            .help("Total number of successful product detail requests").register(collectorRegistry),
        PRODUCT_DETAILS_FAIL to Counter.build().name(PRODUCT_DETAILS_FAIL)
            .help("Total number of failed product detail requests").register(collectorRegistry),
        PRODUCTS_LIST_SUCCESS to Counter.build().name(PRODUCTS_LIST_SUCCESS)
            .help("Total number of successful products list requests").register(collectorRegistry),
        PRODUCTS_LIST_FAIL to Counter.build().name(PRODUCTS_LIST_FAIL)
            .help("Total number of failed products list requests").register(collectorRegistry),
        PRODUCT_CREATE_SUCCESS to Counter.build().name(PRODUCT_CREATE_SUCCESS)
            .help("Total number of successful product create requests").register(collectorRegistry),
        PRODUCT_CREATE_FAIL to Counter.build().name(PRODUCT_CREATE_FAIL)
            .help("Total number of failed product create requests").register(collectorRegistry),
        PRODUCT_DELETE_SUCCESS to Counter.build().name(PRODUCT_DELETE_SUCCESS)
            .help("Total number of successful product delete requests").register(collectorRegistry),
        PRODUCT_DELETE_FAIL to Counter.build().name(PRODUCT_DELETE_FAIL)
            .help("Total number of failed product delete requests").register(collectorRegistry),
        PRODUCT_UPDATE_SUCCESS to Counter.build().name(PRODUCT_UPDATE_SUCCESS)
            .help("Total number of successful product update requests").register(collectorRegistry),
        PRODUCT_UPDATE_FAIL to Counter.build().name(PRODUCT_UPDATE_FAIL)
            .help("Total number of failed product update requests").register(collectorRegistry),

        USER_LIST_SUCCESS to Counter.build().name(USER_LIST_SUCCESS)
            .help("Total number of successful user list requests").register(collectorRegistry),
        USER_LIST_FAIL to Counter.build().name(USER_LIST_FAIL)
            .help("Total number of failed user list requests").register(collectorRegistry),
        USER_DETAIL_SUCCESS to Counter.build().name(USER_DETAIL_SUCCESS)
            .help("Total number of successful user detail requests").register(collectorRegistry),
        USER_DETAIL_FAIL to Counter.build().name(USER_DETAIL_FAIL)
            .help("Total number of failed user detail requests").register(collectorRegistry),
        USER_CREATE_SUCCESS to Counter.build().name(USER_CREATE_SUCCESS)
            .help("Total number of successful user create requests").register(collectorRegistry),
        USER_CREATE_FAIL to Counter.build().name(USER_CREATE_FAIL)
            .help("Total number of failed user create requests").register(collectorRegistry),
        USER_DELETE_SUCCESS to Counter.build().name(USER_DELETE_SUCCESS)
            .help("Total number of successful user delete requests").register(collectorRegistry),
        USER_DELETE_FAIL to Counter.build().name(USER_DELETE_FAIL)
            .help("Total number of failed user delete requests").register(collectorRegistry),
        USER_UPDATE_SUCCESS to Counter.build().name(USER_UPDATE_SUCCESS)
            .help("Total number of successful user update requests").register(collectorRegistry),
        USER_UPDATE_FAIL to Counter.build().name(USER_UPDATE_FAIL)
            .help("Total number of failed user update requests").register(collectorRegistry)
    )

    fun incrementCounter(counterName: String) {
        counters[counterName]?.inc()
    }
}