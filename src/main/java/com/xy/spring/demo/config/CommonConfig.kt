package com.xy.spring.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class CommonConfig : WebMvcConfigurer {

    @Autowired
    lateinit var loginInterceptor: LoginInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loginInterceptor)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        // 添加CORS跨域支持
        registry.addMapping("/**")
                // 允许所有的请求方法
                .allowedMethods("*")
    }
}