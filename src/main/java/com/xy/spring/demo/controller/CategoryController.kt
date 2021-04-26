package com.xy.spring.demo.controller

import com.github.pagehelper.PageHelper
import com.xy.spring.demo.entity.CategoryEntity
import com.xy.spring.demo.entity.ResponseEntity
import com.xy.spring.demo.service.CategoryService
import com.xy.spring.demo.service.UserService
import com.xy.spring.demo.util.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/category"])
@Api(tags = ["CategoryEntity"])
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @ApiOperation(value = "商品类别查询")
    @RequestMapping(value = ["/categories"], method = [RequestMethod.GET])
    fun getUserList(@RequestParam(value = "pageIndex", defaultValue = "1") @ApiParam("起始页编号") pageIndex: Int,
                    @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页显示的数量") pageSize: Int): ResponseEntity {
        PageHelper.startPage<CategoryEntity>(pageIndex, pageSize)
        val categoryList = categoryService.getCategoryList()
        return ResponseEntity.success(data = getPageData(categoryList))
    }
}