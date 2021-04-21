package com.xy.spring.demo.controller

import com.xy.spring.demo.entity.ResponseEntity
import com.xy.spring.demo.service.UserService
import com.xy.spring.demo.util.ResponseCode
import com.xy.spring.demo.util.addUser
import com.xy.spring.demo.util.currentDate
import com.xy.spring.demo.util.getKey
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
                     @RequestParam(value = "orderId") @ApiParam("订单ID") orderId: String): Any {
        System.out.println("registration $userName, $password, $imoocId, $orderId")
        userService.addUser(userName, bCryptPasswordEncoder.encode(password), imoocId, orderId, currentDate())
        return ResponseEntity.success("registration success.")
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@RequestParam(value = "userName") @ApiParam("用户名") userName: String,
              @RequestParam(value = "password") @ApiParam("密码") password: String,
              httpServletRequest: HttpServletRequest): Any {
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
}