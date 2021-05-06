package com.xy.spring.demo.service

import com.xy.spring.demo.entity.CityEntity
import com.xy.spring.demo.mapper.CityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class CityService {

    @Autowired
    open lateinit var cityMapper: CityMapper

    open fun getCities(): List<CityEntity> {
        return cityMapper.getCities()
    }
}