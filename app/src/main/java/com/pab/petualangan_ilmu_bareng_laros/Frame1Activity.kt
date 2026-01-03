package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class Frame1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Pakai layout frame_1 sebagai tampilan pertama
        setContentView(R.layout.activity_frame1)

        // Tombol PLAY: pindah ke Frame2Activity
        val playBtn: ImageButton? = findViewById(R.id.playButton)
        playBtn?.setOnClickListener {
            val i = Intent(this, Frame2Activity::class.java)
            startActivity(i)
        }

        // Tombol Masuk: pindah ke MainActivity (activity_main.xml)
        val masukBtn: ImageButton? = findViewById(R.id.btnMasuk)
        masukBtn?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
