package com.example.messengerapi.controllers

import com.example.messengerapi.components.MessageAssambler
import com.example.messengerapi.helpers.MessageVO
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import com.example.messengerapi.services.MessageServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/messages")
class MessagesController(
    val messagesService: MessageServiceImpl,
    val userRepository: UserRepository,
    val messageAssambler: MessageAssambler
) {
    @PostMapping
    fun create(
        @RequestBody masageDetails: MessageRequest,
        request: HttpServletRequest
    ): ResponseEntity<MessageVO> {
        val principal = request.userPrincipal
        val sender = userRepository.findByUserName(principal.name) as User
        val message = messagesService.sendMessage(
            sender,
            masageDetails.recipientId,
            masageDetails.message,
        )
        return ResponseEntity.ok(messageAssambler.toMessageVo(message))
    }

    data class MessageRequest(val recipientId: Long, val message: String)
}