package com.xy.spring.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = ["pwd"])
data class UserEntity(

        /** 用户ID  */
        val uid: String?,
        /** imooc用户ID  */
        val imoocId: String?,
        /** 订单ID  */
        val orderId: String?,
        /** 用户名  */
        val userName: String?,
        /** 密码  */
        val pwd: String?,
        /** 创建时间  */
        val createTime: String?,
        /** 是否被禁用  */
        val forbid: String?
)