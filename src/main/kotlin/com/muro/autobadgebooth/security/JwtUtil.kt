package com.muro.autobadgebooth.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class JwtUtil : Serializable {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    //retrieve username from jwt token
    fun getUsernameFromToken(token: String?): String = getAllClaimsFromToken(token).subject

    //retrieve expiration date from jwt token
    fun getExpirationDateFromToken(token: String?): Date = getAllClaimsFromToken(token).expiration

    //for retrieveing any information from token we will need the secret key
    private fun getAllClaimsFromToken(token: String?) = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body

    //check if the token has expired
    private fun isTokenExpired(token: String?) = getExpirationDateFromToken(token).before(Date())


    //generate token for user
    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        return doGenerateToken(claims, userDetails.username)
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    //validate token
    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    companion object {
        private const val serialVersionUID = 1L
        private const val TOKEN_EXPIRATION_TIME = 60 * 60 * 4 // 4 HOURS
    }
}