package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.content.Intent

class Frame5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame5)

        // Tombol rumah: kembali ke tampilan utama (MainActivity / activity_main.xml)
        findViewById<ImageButton>(R.id.btnHome)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Tombol mulai: pindah ke Frame6Activity (tampilan frame_9)
        findViewById<ImageButton>(R.id.btnMulai)?.setOnClickListener {
            startActivity(Intent(this, Frame6Activity::class.java))
        }
    }
}
