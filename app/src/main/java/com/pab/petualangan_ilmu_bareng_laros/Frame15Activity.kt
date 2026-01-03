package com.pab.petualangan_ilmu_bareng_laros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Frame15Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame15)

        findViewById<View>(R.id.btnFrame15Back)?.setOnClickListener {
            finish()
        }
    }
}

