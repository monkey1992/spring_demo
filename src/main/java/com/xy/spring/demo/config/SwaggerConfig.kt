package com.xy.spring.demo.config

import org.springframework.context.annotation.Configuration
import springfox.documentation.annotations.ApiIgnore
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Swagger配置文件
 * 通过 protocol://domain:port/swagger-ui.html访问
 * 没有该类Swagger默认也是可以访问的
 */
@Configuration
@EnableSwagger2
open class SwaggerConfig {

    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xy.spring.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore::class.java)
                .enableUrlTemplating(false)
                .tags(Tag("Account", "账号模块"))
                .tags(Tag("CategoryEntity", "商品类别"))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API文档")
                .description("Spring Demo API文档")
                .version(VERSION)
                .contact(Contact(AUTHOR, "https://www.baidu.com", "yuriyshea@163.com"))
                .build()
    }

    companion object {

        private const val VERSION = "1.0.1"
        private const val AUTHOR = "Spring Demo"
    }
}