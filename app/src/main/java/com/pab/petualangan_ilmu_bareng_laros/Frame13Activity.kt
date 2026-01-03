package com.pab.petualangan_ilmu_bareng_laros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Frame13Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame13)

        findViewById<View>(R.id.btnFrame13Back)?.setOnClickListener {
            startActivity(Intent(this, Frame12Activity::class.java))
            finish()
        }

        findViewById<View>(R.id.btnFrame13Forward)?.setOnClickListener {
            startActivity(Intent(this, Frame14Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame15)?.setOnClickListener {
            startActivity(Intent(this, Frame15Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame16)?.setOnClickListener {
            startActivity(Intent(this, Frame16Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame17)?.setOnClickListener {
            startActivity(Intent(this, Frame17Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame18)?.setOnClickListener {
            startActivity(Intent(this, Frame18Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame19)?.setOnClickListener {
            startActivity(Intent(this, Frame19Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame20)?.setOnClickListener {
            startActivity(Intent(this, Frame20Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame21)?.setOnClickListener {
            startActivity(Intent(this, Frame21Activity::class.java))
        }

        findViewById<View>(R.id.btnFrame13ToFrame22)?.setOnClickListener {
            startActivity(Intent(this, Frame22Activity::class.java))
        }
    }
}
