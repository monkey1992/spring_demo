package com.xy.spring.demo.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/"])
class TestController {

    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    fun hello(): Any {
        return "hello"
    }
}