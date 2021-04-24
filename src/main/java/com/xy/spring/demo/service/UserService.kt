package com.xy.spring.demo.service

import com.xy.spring.demo.entity.UserEntity
import com.xy.spring.demo.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class UserService {

    @Autowired
    open lateinit var userMapper: UserMapper

    open fun addUser(userName: String, password: String, imoocId: String, orderId: String, createTime: String) {
        userMapper.addUser(userName, password, imoocId, orderId, createTime)
    }

    open fun findUser(userName: String): List<UserEntity>? {
        return userMapper.findUser(userName)
    }

    open fun getUserList(): List<UserEntity>? {
        return userMapper.getUserList()
    }

    open fun updateUser(uid: String, forbid: String) {
        userMapper.updateUser(uid, forbid)
    }
}