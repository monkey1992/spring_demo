package com.xy.spring.demo.util

import com.github.pagehelper.PageInfo

fun <T> getPageData(list: List<T>?): Map<String, Any> {
    val pageInfo = PageInfo<T>(list)
    val data = HashMap<String, Any>()
    data["total"] = pageInfo.total
    data["list"] = pageInfo.list
    return data
}