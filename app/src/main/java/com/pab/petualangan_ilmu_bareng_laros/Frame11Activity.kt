package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Frame11Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame11)

        // Menutup activity ketika pengguna mengetuk di mana saja pada overlay
        findViewById<View>(R.id.tapToCloseOverlay)?.setOnClickListener {
            finish()
        }
    }
}
