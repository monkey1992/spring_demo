package com.xy.spring.demo.config

import com.xy.spring.demo.util.checkUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
open class LoginInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }

        val clazz = handler.beanType
        val method = handler.method
        if (!clazz.isAnnotationPresent(NeedLogin::class.java) && !method.isAnnotationPresent(NeedLogin::class.java)) {
            return true
        }

        if (checkUser(redisTemplate, request)) {
            return true
        }

        response.status = 401
        response.contentType = "text/html;charset=utf-8"
        response.writer.write("Please login first.")

        return false
    }
}