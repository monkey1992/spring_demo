package com.xy.spring.demo.mapper

import com.xy.spring.demo.entity.CategoryEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CategoryMapper {

    fun addCategory(categoryName: String, createTime: String)

    fun findCategory(categoryName: String): List<CategoryEntity>?

    fun getCategoryList(): List<CategoryEntity>?

    fun updateCategory(categoryId: String, categoryName: String)
}