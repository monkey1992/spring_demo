package com.xy.spring.demo.entity

import com.fasterxml.jackson.annotation.JsonInclude

import javax.persistence.*
import java.io.Serializable
import java.util.Date

/**
 * @param id 城市id
 * @param pid 父id
 * @param district_name 城市的名字
 * @param type 城市的类型，0是国，1是省，2是市，3是区
 * @param hierarchy 地区所处的层级
 * @param district_sqe 层级序列
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CityEntity(
        val id: String,
        val pid: String,
        val district_name: String,
        val type: String,
        val hierarchy: String,
        val district_sqe: String
)