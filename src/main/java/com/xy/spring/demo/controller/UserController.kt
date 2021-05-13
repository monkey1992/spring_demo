package com.xy.spring.demo.controller

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.xy.spring.demo.config.NeedLogin
import com.xy.spring.demo.entity.ResponseEntity
import com.xy.spring.demo.entity.UserEntity
import com.xy.spring.demo.service.UserService
import com.xy.spring.demo.util.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/user"])
@Api(tags = ["Account"])
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @ApiOperation(value = "注册")
    @RequestMapping(value = ["/registration"], method = [RequestMethod.POST])
    fun registration(@RequestParam(value = "userName") @ApiParam("用户名") userName: String,
                     @RequestParam(value = "password") @ApiParam("密码") password: String,
                     @RequestParam(value = "imoocId") @ApiParam("imoocId") imoocId: String,
                     @RequestParam(value = "orderId") @ApiParam("订单ID") orderId: String): ResponseEntity {
        System.out.println("registration $userName, $password, $imoocId, $orderId")
        userService.addUser(userName, bCryptPasswordEncoder.encode(password), imoocId, orderId, currentDate())
        return ResponseEntity.success("registration success.")
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@RequestParam(value = "userName") @ApiParam("用户名") userName: String,
              @RequestParam(value = "password") @ApiParam("密码") password: String,
              httpServletRequest: HttpServletRequest): ResponseEntity {
        System.out.println("login $userName, $password")
        val userEntities = userService.findUser(userName)
        if (userEntities.isNullOrEmpty()) {
            return ResponseEntity.of(ResponseCode.RC_ACCOUNT_INVALID)
        }
        val userEntity = userEntities.firstOrNull { userEntity -> bCryptPasswordEncoder.matches(password, userEntity.pwd) }
                ?: return ResponseEntity.of(ResponseCode.RC_PWD_INVALID)
        if (userEntity.forbid == "1") {
            return ResponseEntity.of(ResponseCode.RC_USER_FORBID)
        }
        val session = httpServletRequest.session
        addUser(redisTemplate, session, userEntity)
        return ResponseEntity.success("登录成功", getKey(session))
    }

    @NeedLogin
    @ApiOperation(value = "登出")
    @RequestMapping(value = ["/logout"])
    fun logout(request: HttpServletRequest): ResponseEntity {
        val session = request.session
        removeUser(redisTemplate, session)
        return ResponseEntity.success("logout success")
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = ["/users"], method = [RequestMethod.GET])
    fun getUserList(@RequestParam(value = "pageIndex", defaultValue = "1") @ApiParam("起始页编号") pageIndex: Int,
                    @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页显示的数量") pageSize: Int): ResponseEntity {
        PageHelper.startPage<UserEntity>(pageIndex, pageSize)
        val userList = userService.getUserList()
        return ResponseEntity.success(data = getPageData(userList))
    }

    @ApiOperation(value = "用户管理 ")
    @RequestMapping(value = ["/{uid}"], method = [RequestMethod.PUT])
    fun updateUser(@PathVariable @ApiParam("用户ID") uid: String,
                   @RequestParam(value = "forbid") @ApiParam("是否禁用") forbid: String): ResponseEntity {
        userService.updateUser(uid, forbid)
        return ResponseEntity.success("操作成功")
    }
}