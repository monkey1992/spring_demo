package com.xy.spring.demo.controller

import com.xy.spring.demo.entity.ResponseEntity
import com.xy.spring.demo.service.CityService
import com.xy.spring.demo.util.getPageData
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/"])
@Api(tags = ["City"])
class CityController {

    @Autowired
    lateinit var cityService: CityService

    @ApiOperation(value = "查询城市")
    @RequestMapping(value = ["/cities"], method = [RequestMethod.GET])
    fun getCities(): ResponseEntity {
        return ResponseEntity.success(data = getPageData(cityService.getCities()))
    }
}