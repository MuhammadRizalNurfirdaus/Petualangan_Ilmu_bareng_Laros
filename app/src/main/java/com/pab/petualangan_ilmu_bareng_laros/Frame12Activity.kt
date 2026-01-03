package com.pab.petualangan_ilmu_bareng_laros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Frame12Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame12)

        findViewById<View>(R.id.btnFrame12Home)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<View>(R.id.btnFrame12ToFrame13)?.setOnClickListener {
            startActivity(Intent(this, Frame13Activity::class.java))
        }
    }
}
