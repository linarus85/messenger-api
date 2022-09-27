package com.example.messengerapi.exeptions

class MassageEmptyExeptions (override val message: String= "status cannot be empty")
    : RuntimeException()
