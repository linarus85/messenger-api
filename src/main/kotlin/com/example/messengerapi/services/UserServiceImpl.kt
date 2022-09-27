package com.example.messengerapi.services

import com.example.messengerapi.exeptions.InvalidUserIdExeptions
import com.example.messengerapi.exeptions.UserStatusEmptyExeption
import com.example.messengerapi.exeptions.UsernameUnvailitableException
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class UserServiceImpl(val repository: UserRepository) : UserService {

    @Throws(UsernameUnvailitableException::class)
    override fun attemtRegistration(userDetails: User): User {
        if (!usernameExists(userDetails.username)) {
            val user = User()
            user.username = userDetails.username
            user.phoneNumber = userDetails.phoneNumber
            user.password = userDetails.password
            repository.save(user)
            obscurePassword(user)
            return user
        }
        throw UsernameUnvailitableException("${userDetails.username} is unavailable")
    }

    @Throws(UserStatusEmptyExeption::class)
     fun updateUserStatus(currentUser: User, updateDetails: User): User {
        if (updateDetails.status.isNotEmpty()) {
            currentUser.status = updateDetails.status
            repository.save(currentUser)
            return currentUser
        }
        throw UserStatusEmptyExeption()
    }


    private fun obscurePassword(user: User?) {
        user?.password = "XXX XXXX XXX"
    }

    override fun listUsers(currentUser: User): List<User> {
        return repository.findAll().mapTo(ArrayList(), { it })
            .filter { it != currentUser }
    }

    override fun retriveUserData(username: String): User? {
        val user = repository.findByUserName(username)
        obscurePassword(user)
        return user
    }

    @Throws(InvalidUserIdExeptions::class)
    override fun retriveUserData(id: Long): User? {
        val userOptional = repository.findById(id)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            obscurePassword(user)
            return user
        }
        throw InvalidUserIdExeptions("'$id' does not exist")
    }

    override fun usernameExists(username: String): Boolean {
        return repository.findByUserName(username) != null
    }


}