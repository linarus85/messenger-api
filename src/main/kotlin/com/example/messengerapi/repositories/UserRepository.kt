package com.example.messengerapi.repositories

import com.example.messengerapi.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUserName(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}