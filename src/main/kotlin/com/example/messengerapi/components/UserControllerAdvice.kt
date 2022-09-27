package com.example.messengerapi.components

import com.example.messengerapi.constanse.ErrorResponce
import com.example.messengerapi.constanse.ResposeConstants
import com.example.messengerapi.exeptions.InvalidUserIdExeptions
import com.example.messengerapi.exeptions.UserStatusEmptyExeption
import com.example.messengerapi.exeptions.UsernameUnvailitableException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserControllerAdvice {
    @ExceptionHandler(UsernameUnvailitableException::class)
    fun UsernameUnvailitable(
        usernameUnvailitableException:UsernameUnvailitableException
    ):ResponseEntity<ErrorResponce>{
        val res = ErrorResponce(ResposeConstants.USERNAME_UNAVALABLE.value,
        usernameUnvailitableException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(InvalidUserIdExeptions::class)
    fun invalidId(
        invalidUserIdExeptions:InvalidUserIdExeptions
    ):ResponseEntity<ErrorResponce>{
        val res = ErrorResponce(
            ResposeConstants.INVALID_USER_ID.value,
            invalidUserIdExeptions.message)
        return ResponseEntity.badRequest().body(res)
    }

    @ExceptionHandler(UserStatusEmptyExeption::class)
    fun statusEmpty(
        userStatusEmptyExeption:UserStatusEmptyExeption
    ):ResponseEntity<ErrorResponce>{
        val res = ErrorResponce(ResposeConstants.EMPTY_STATUS.value,
            userStatusEmptyExeption.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}