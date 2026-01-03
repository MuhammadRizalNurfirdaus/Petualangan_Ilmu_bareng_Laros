package com.pab.petualangan_ilmu_bareng_laros

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class Frame4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame4)

        val usernameEt: TextInputEditText? = findViewById(R.id.etLoginUsername)
        val passwordEt: TextInputEditText? = findViewById(R.id.etLoginPassword)
        val db = UserDbHelper(this)

        findViewById<ImageButton>(R.id.btnLogin)?.setOnClickListener {
            val username = usernameEt?.text?.toString()?.trim().orEmpty()
            val password = passwordEt?.text?.toString()?.trim().orEmpty()

            if (username.isEmpty()) {
                Toast.makeText(this, "Username wajib diisi", Toast.LENGTH_SHORT).show()
                usernameEt?.requestFocus(); return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Password wajib diisi", Toast.LENGTH_SHORT).show()
                passwordEt?.requestFocus(); return@setOnClickListener
            }

            val ok = db.verifyUser(username, password)
            if (ok) {
                // Arahkan ke activity main setelah login sukses
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Username/Password salah. Silakan masuk terlebih dahulu.", Toast.LENGTH_SHORT).show()
                passwordEt?.requestFocus()
            }
        }
    }
}
