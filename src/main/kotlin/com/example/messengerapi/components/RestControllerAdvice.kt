package com.example.messengerapi.components

import com.example.messengerapi.constanse.ErrorResponce
import com.example.messengerapi.constanse.ResposeConstants
import com.example.messengerapi.exeptions.MessageResipentInvalidExeptions
import com.example.messengerapi.exeptions.UserDeactivatExeption
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {
    @ExceptionHandler(UserDeactivatExeption::class)
    fun userDiactived(
        userDeactivatExeption: UserDeactivatExeption
    ): ResponseEntity<ErrorResponce> {
        val res = ErrorResponce(
            ResposeConstants.ACCOUNT_DIACTIVATED.value,
            userDeactivatExeption.message)
        return ResponseEntity(res,HttpStatus.UNAUTHORIZED)
    }
}