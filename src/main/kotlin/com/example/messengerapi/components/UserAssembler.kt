package com.example.messengerapi.components

import com.example.messengerapi.helpers.UserListVO
import com.example.messengerapi.helpers.UserVO
import com.example.messengerapi.models.User
import org.springframework.stereotype.Component

@Component
class UserAssembler {
    fun toUserVO(user: User): UserVO {
        return UserVO(
            user.id,
            user.username,
            user.phoneNumber,
            user.status,
            user.createAt.toString(),
        )
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userList = users.map { toUserVO(it) }
        return UserListVO(userList)
    }
}