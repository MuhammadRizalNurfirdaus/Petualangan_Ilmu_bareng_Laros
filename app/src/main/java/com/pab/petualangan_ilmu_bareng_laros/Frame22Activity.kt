package com.pab.petualangan_ilmu_bareng_laros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Frame22Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame22)

        // Tombol kembali ke Frame13Activity
        findViewById<View>(R.id.btnFrame22Back)?.setOnClickListener {
            startActivity(Intent(this, Frame13Activity::class.java))
            finish()
        }
    }
}

