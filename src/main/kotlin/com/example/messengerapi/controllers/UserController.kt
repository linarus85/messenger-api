package com.example.messengerapi.controllers

import com.example.messengerapi.components.UserAssembler
import com.example.messengerapi.helpers.UserListVO
import com.example.messengerapi.helpers.UserVO
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import com.example.messengerapi.services.UserService
import com.example.messengerapi.services.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserServiceImpl,
    val userAssembler: UserAssembler,
    val userRepository: UserRepository
) {
    @PostMapping
    @RequestMapping("/register")
    fun create(@Validated @RequestBody userDetails: User):
            ResponseEntity<UserVO>{
        val user = userService.attemtRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }
    @GetMapping
    @RequestMapping("/{user_id}")
    fun show(@PathVariable("user_id") userId:Long):ResponseEntity<UserVO>{
        val user = userService.retriveUserData(userId)as User
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }
    @GetMapping
    @RequestMapping("/details")
    fun echoDetails(request: HttpServletRequest):ResponseEntity<UserVO>{
        val user = userRepository.findByUserName(request.userPrincipal.name)as User
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }
    @GetMapping
    fun index(request: HttpServletRequest):ResponseEntity<UserListVO>{
        val user = userRepository.findByUserName(request.userPrincipal.name)as User
        val users = userService.listUsers(user)
        return ResponseEntity.ok(userAssembler.toUserListVO(users))
    }
    @PutMapping
    fun update(@RequestBody updateDetails:User,
    request: HttpServletRequest):ResponseEntity<UserVO>{
        val currentUser = userRepository.findByUserName(request.userPrincipal.name)
         userService.updateUserStatus(currentUser as User,updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}