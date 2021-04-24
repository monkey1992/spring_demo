package com.xy.spring.demo.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.xy.spring.demo.util.ResponseCode

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseEntity(val code: Int,
                          var message: String,
                          var data: Any? = null,
                          var extra: Map<String, String>? = null) {

    fun addParams(key: String, value: Any) {
        if (data == null) {
            data = hashMapOf<String, Any>()
        }
        (data as? HashMap<String, Any>)?.put(key, value)
    }

    companion object {

        fun of(responseCode: ResponseCode): ResponseEntity {
            return ResponseEntity(responseCode.code, responseCode.msg)
        }

        @JvmOverloads
        fun success(msg: String = "SUCCESS", data: Any? = null): ResponseEntity {
            return of(ResponseCode.RC_SUCCESS).apply {
                this.message = msg
                this.data = data
            }
        }
    }
}