package com.example.messengerapi.filters

import com.example.messengerapi.security.TokenAuthServise
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.jvm.Throws

class JWTAuthFilter : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest?, res: ServletResponse?, filterChain: FilterChain?) {
        val auth = TokenAuthServise
            .getAuth(req as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = auth
        filterChain?.doFilter(req, res)
    }
}