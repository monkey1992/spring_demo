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
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.*
import java.sql.SQLIntegrityConstraintViolationException

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

    @ApiOperation(value = "添加商品类别")
    @RequestMapping(value = [""], method = [RequestMethod.PUT])
    fun addCategory(@RequestParam(value = "categoryName") @ApiParam("商品类别") categoryName: String): ResponseEntity {
        return try {
            categoryService.addCategory(categoryName)
            ResponseEntity.success(msg = "操作成功")
        } catch (e: DuplicateKeyException) {
            e.printStackTrace()
            ResponseEntity.of(ResponseCode.RC_ERROR).apply {
                message = "商品类别重复"
            }
        }
    }

    @ApiOperation(value = "删除商品类别")
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun removeCategory(@ApiParam("商品类别ID") @PathVariable id: String): ResponseEntity {
        categoryService.removeCategory(id)
        return ResponseEntity.success(msg = "删除成功")
    }
}