package com.pab.petualangan_ilmu_bareng_laros

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

object SecurityUtils {
    private val secureRandom = SecureRandom()

    fun generateSalt(byteLen: Int = 16): String {
        val bytes = ByteArray(byteLen)
        secureRandom.nextBytes(bytes)
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    fun hashPassword(password: String, saltBase64: String): String {
        val salt = Base64.decode(saltBase64, Base64.NO_WRAP)
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt)
        val hash = md.digest(password.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }
}
