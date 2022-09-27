package com.example.messengerapi.exeptions

class UserStatusEmptyExeption (override val message: String = "status cannot be empty")
    : RuntimeException()
