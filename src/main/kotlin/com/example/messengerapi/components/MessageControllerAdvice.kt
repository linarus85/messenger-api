package com.example.messengerapi.components

import com.example.messengerapi.constanse.ErrorResponce
import com.example.messengerapi.constanse.ResposeConstants
import com.example.messengerapi.exeptions.InvalidUserIdExeptions
import com.example.messengerapi.exeptions.MassageEmptyExeptions
import com.example.messengerapi.exeptions.MessageResipentInvalidExeptions
import com.example.messengerapi.exeptions.UserStatusEmptyExeption
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class MessageControllerAdvice {
    @ExceptionHandler(MassageEmptyExeptions::class)
    fun messageEmpty(
        massageEmptyExeptions: MassageEmptyExeptions
    ): ResponseEntity<ErrorResponce> {
        val res = ErrorResponce(
            ResposeConstants.MESSAGE_EMPTY.value,
            massageEmptyExeptions.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(MessageResipentInvalidExeptions::class)
    fun messageRespient(
        messageResipentInvalidExeptions: MessageResipentInvalidExeptions
    ): ResponseEntity<ErrorResponce> {
        val res = ErrorResponce(
            ResposeConstants.MESSAGE_RESIPENT.value,
            messageResipentInvalidExeptions.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}