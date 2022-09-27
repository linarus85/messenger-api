package com.example.messengerapi.components

import com.example.messengerapi.exeptions.UserDeactivatExeption
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class AccountValidateInterceptor(val userRepository: UserRepository) :
    HandlerInterceptorAdapter() {
    @Throws(UserDeactivatExeption::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal: Principal? = request.userPrincipal
        if (principal!=null){
            val user = userRepository.findByUserName(principal.name)
            as User
            if (user.accountStatus=="deactivated"){
                throw UserDeactivatExeption("Account has been deactivated")
            }
        }
        return super.preHandle(request, response, handler)
    }
}