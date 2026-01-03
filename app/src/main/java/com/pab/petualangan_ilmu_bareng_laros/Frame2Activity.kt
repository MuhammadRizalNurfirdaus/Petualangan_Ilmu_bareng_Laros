package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.content.Intent

class Frame2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame2)

        // Button listeners
        findViewById<ImageButton>(R.id.btnDaftar)?.setOnClickListener {
            startActivity(Intent(this, Frame3Activity::class.java))
        }

        findViewById<ImageButton>(R.id.btnMasuk)?.setOnClickListener {
            // Pindah ke tampilan Frame 4
            startActivity(Intent(this, Frame4Activity::class.java))
        }
    }
}

