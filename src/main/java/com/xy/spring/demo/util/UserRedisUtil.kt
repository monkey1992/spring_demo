package com.xy.spring.demo.util

import com.xy.spring.demo.entity.UserEntity
import org.springframework.data.redis.core.StringRedisTemplate
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

const val BOARDING_PASS = "boarding-pass"

/**
 * 将用户信息保存到redis
 */
fun addUser(redisTemplate: StringRedisTemplate, httpSession: HttpSession, userEntity: UserEntity) {
    redisTemplate.opsForValue().set(getKey(httpSession), userEntity.toJsonString().orEmpty())
}

/**
 * 将用户信息从redis中移除
 */
fun removeUser(redisTemplate: StringRedisTemplate, httpSession: HttpSession) {
    httpSession.invalidate()
    redisTemplate.delete(getKey(httpSession))
}

/**
 * 获取用户信息
 */
fun getUser(redisTemplate: StringRedisTemplate, httpServletRequest: HttpServletRequest): UserEntity? {
    return fromJson(redisTemplate.opsForValue().get(httpServletRequest.getBoardingPass()), UserEntity::class.java)
}

/**
 * 检查Redis中是否存在用户
 */
fun checkUser(redisTemplate: StringRedisTemplate, request: HttpServletRequest): Boolean {
    return redisTemplate.opsForValue().get(request.getBoardingPass()) != null
}

fun getKey(httpSession: HttpSession): String {
    return httpSession.id
}

fun HttpServletRequest.getBoardingPass(): String {
    return getHeader(BOARDING_PASS) ?: "no-pass"
}