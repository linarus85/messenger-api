package com.example.messengerapi.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal object TokenAuthServise {
    private const val TOKEN_EXPIRY: Long = 864000000
    private const val SECRET = "$78grj9re9v9j878j9679786656bbc"
    private const val TOKEN_PREFIX = "Bearer"
    private const val AUTHORIZATION_HEADER_KEY = "Authorization"

    fun addAuth(response: HttpServletResponse?, name: String?) {
        val JWT = Jwts.builder()
            .setSubject(name)
            .setExpiration(
                Date(
                    System.currentTimeMillis() + TOKEN_EXPIRY
                )
            )
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
        response?.addHeader(AUTHORIZATION_HEADER_KEY, "$TOKEN_PREFIX $JWT")
    }

    fun getAuth(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(AUTHORIZATION_HEADER_KEY)
        if (token != null) {
            val user = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body.subject
            if (user != null) {
                return UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    emptyList<GrantedAuthority>()
                )
            }
        }
        return null
    }

}

