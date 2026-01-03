package com.pab.petualangan_ilmu_bareng_laros

import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
 
import com.google.android.material.textfield.TextInputEditText
 

class Frame3Activity : AppCompatActivity() {

    private var photoUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            photoUri = it
            findViewById<ImageView>(R.id.photoView)?.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame3)

        // Optional photo picker
        findViewById<ImageView>(R.id.photoView)?.setOnClickListener {
            pickImage.launch("image/*")
        }

    val usernameEt: TextInputEditText? = findViewById(R.id.etUsername)
    val passwordEt: TextInputEditText? = findViewById(R.id.etPassword)
    val db = UserDbHelper(this)

        // Password visibility toggle is now handled by TextInputLayout's endIconMode="password_toggle"

        // Daftar button: require username and password, then return to Frame 2
        findViewById<ImageButton>(R.id.btnRegister)?.setOnClickListener {
            val username = usernameEt?.text?.toString()?.trim().orEmpty()
            val password = passwordEt?.text?.toString()?.trim().orEmpty()

            if (username.isEmpty()) {
                Toast.makeText(this, "Username wajib diisi", Toast.LENGTH_SHORT).show()
                usernameEt?.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Password wajib diisi", Toast.LENGTH_SHORT).show()
                passwordEt?.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(this, "Minimal 6 karakter", Toast.LENGTH_SHORT).show()
                passwordEt?.requestFocus()
                return@setOnClickListener
            }

            if (db.userExists(username)) {
                Toast.makeText(this, "Username sudah dipakai", Toast.LENGTH_SHORT).show()
                usernameEt?.requestFocus()
                return@setOnClickListener
            }

            val rowId = db.insertUser(username, password, photoUri?.toString())
            if (rowId > 0) {
                Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                finish() // kembali ke Frame 2
            } else {
                Toast.makeText(this, "Gagal menyimpan, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
