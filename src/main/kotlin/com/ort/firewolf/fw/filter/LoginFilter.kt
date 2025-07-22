package com.ort.firewolf.fw.filter

import com.google.firebase.auth.FirebaseAuth
import com.ort.firewolf.fw.security.FirewolfUser
import com.ort.firewolf.fw.security.FirewolfUserDetailService
import com.ort.firewolf.fw.security.getIpAddress
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * see https://qiita.com/mr-hisa-child/items/5ed2ae2fe4c86d4bb5c7
 */
@Component
class LoginFilter(
    val userService: FirewolfUserDetailService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // コンテキストにログインユーザ情報をセット
        val token = PreAuthenticatedAuthenticationToken(auth(request), null)
        SecurityContextHolder.getContext().authentication = token
        filterChain.doFilter(request, response)
    }

    // ログインユーザ情報を取得
    private fun auth(request: HttpServletRequest): UserDetails? {

        // リクエストヘッダからJWTを取り出す
        val token: String? = getToken(request)
        token ?: return null

        try {
            // JWTを検証、uid取得
            val uid: String? = FirebaseAuth.getInstance().verifyIdToken(token)?.uid
            uid ?: throw BadCredentialsException("改竄リクエストまたはトークン有効期限切れです")

            // ユーザ情報を検索
            return try {
                val user: FirewolfUser = userService.loadUserByUsername(uid) as FirewolfUser
                user.copy(ipAddress = request.getIpAddress())
            } catch (e: UsernameNotFoundException) {
                // uidまで取得できているのでユーザを新規作成
                userService.insertUser(uid)
            }
        } catch (e: Exception) {
            throw BadCredentialsException(e.message, e)
        }
    }

    // リクエストヘッダからトークンを取得
    private fun getToken(request: HttpServletRequest): String? {
        val token: String? = request.getHeader("Authorization")
        if (token == null || !token.startsWith("Bearer ")) {
            return null
        }
        return token.substring("Bearer ".length)
    }
}