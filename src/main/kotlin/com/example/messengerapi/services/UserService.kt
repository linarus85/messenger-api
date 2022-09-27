package com.example.messengerapi.services

import com.example.messengerapi.models.User

interface UserService {
    fun attemtRegistration(userDetails: User): User
    fun listUsers(currentUser: User): List<User>
    fun retriveUserData(username: String): User?
    fun retriveUserData(id: Long): User?
    fun usernameExists(username: String): Boolean
}