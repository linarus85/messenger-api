package com.example.messengerapi.controllers

import com.example.messengerapi.components.ConversationAssambler
import com.example.messengerapi.helpers.ConversationListVO
import com.example.messengerapi.helpers.ConversationVO
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import com.example.messengerapi.services.ConversationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(
    val conversationService: ConversationServiceImpl,
    val conversationAssambler: ConversationAssambler,
    val userRepository: UserRepository
) {
    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        val conversation = conversationService.listUserConversarion(user.id)
        return ResponseEntity.ok(
            conversationAssambler.toConversationListVO(
                conversation,
                user.id
            )
        )
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    fun show(
        @PathVariable(name = "conversation_id") conversationId: Long,
        request: HttpServletRequest
    ): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        val conversationThread = conversationService.retriveThread(conversationId)
        return ResponseEntity.ok(
            conversationAssambler.toConversationVO(
                conversationThread,
                user.id
            )
        )
    }
}