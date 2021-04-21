package com.xy.spring.demo.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.omg.CORBA.Object

fun Any.toJsonString(): String? {

    return try {
        ObjectMapper().writerWithDefaultPrettyPrinter()?.writeValueAsString(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun <T> fromJson(json: String?, tClass: Class<T>): T? {
    if (json.isNullOrBlank()) {
        return null
    }
    return try {
        ObjectMapper().readValue(json, tClass)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}