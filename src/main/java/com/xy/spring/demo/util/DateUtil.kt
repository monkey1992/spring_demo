package com.xy.spring.demo.util

import java.text.SimpleDateFormat
import java.util.*

val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

fun currentDate(): String {
    return simpleDateFormat.format(Date())
}