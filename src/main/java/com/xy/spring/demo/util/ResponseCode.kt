package com.xy.spring.demo.util

enum class ResponseCode {

    RC_SUCCESS(0, "SUCCESS."),
    RC_ACCOUNT_INVALID(5001, "账号不存在"),
    RC_PWD_INVALID(5002, "密码错误"),
    RC_NEED_LOGIN(5003, "请先登录"),
    RC_USER_FORBID(6001, "用户信息非法"),
    RC_ERROR(1001, "存在错误");

    val code: Int
    val msg: String

    constructor(code: Int, msg: String) {
        this.code = code
        this.msg = msg
    }
}