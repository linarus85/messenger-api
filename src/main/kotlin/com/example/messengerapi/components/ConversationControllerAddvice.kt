package com.example.messengerapi.components

import com.example.messengerapi.constanse.ErrorResponce
import com.example.messengerapi.constanse.ResposeConstants
import com.example.messengerapi.exeptions.ConversionsInvalinExeptions
import com.example.messengerapi.exeptions.InvalidUserIdExeptions
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAddvice {
    @ExceptionHandler
    fun conversationEnvalidExeptions(
        conversionsInvalinExeptions: ConversionsInvalinExeptions
    ): ResponseEntity<ErrorResponce> {
        val res = ErrorResponce(
            "",
            conversionsInvalinExeptions.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}