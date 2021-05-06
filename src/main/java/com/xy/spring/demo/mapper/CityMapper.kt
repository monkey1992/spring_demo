package com.xy.spring.demo.mapper

import com.xy.spring.demo.entity.CityEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CityMapper {

    fun getCities(): List<CityEntity>
}