package com.xy.spring.demo.mapper

import com.xy.spring.demo.entity.UserEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    fun addUser(userName: String, password: String, imoocId: String, orderId: String, createTime: String)

    fun findUser(userName: String): List<UserEntity>?

    fun getUserList(): List<UserEntity>?

    fun updateUser(uid: String, forbid: String)
}