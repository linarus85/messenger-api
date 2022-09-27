package com.example.messengerapi.services

import com.example.messengerapi.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class AppUserDetailService(val userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails{
        val user = userRepository.findByUserName(username)?:
        throw  UsernameNotFoundException("$username doesn't exists")
        return User(user.username,user.password,ArrayList<GrantedAuthority>())
    }
}
