package com.xy.spring.demo.service

import com.xy.spring.demo.entity.CategoryEntity
import com.xy.spring.demo.mapper.CategoryMapper
import com.xy.spring.demo.util.currentDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class CategoryService {

    @Autowired
    open lateinit var categoryMapper: CategoryMapper

    open fun addCategory(categoryName: String) {
        categoryMapper.addCategory(categoryName, currentDate())
    }

    fun removeCategory(categoryId: String) {
        categoryMapper.removeCategory(categoryId)
    }

    open fun findCategory(categoryName: String): List<CategoryEntity>? {
        return categoryMapper.findCategory(categoryName)
    }

    open fun getCategoryList(): List<CategoryEntity>? {
        return categoryMapper.getCategoryList()
    }

    open fun updateCategory(categoryId: String, categoryName: String) {
        categoryMapper.updateCategory(categoryId, categoryName)
    }
}