package com.xy.spring.demo.entity

import com.fasterxml.jackson.annotation.JsonInclude

import javax.persistence.*
import java.io.Serializable
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CategoryEntity(

        /** 商品类别ID  */
        val categoryId: Int? = null,

        /** 类别名  */
        val categoryName: String? = null,

        /** 创建时间  */
        val createTime: Date? = null
)