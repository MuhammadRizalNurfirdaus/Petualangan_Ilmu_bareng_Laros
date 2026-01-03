package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class Frame6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame6)

        // Tombol untuk membuka Frame7Activity
        val toFrame7Btn: ImageButton? = findViewById(R.id.btnToFrame7)
        Log.d("Frame6Activity", "btnToFrame7 found = ${toFrame7Btn != null}")
        toFrame7Btn?.isClickable = true
        toFrame7Btn?.isFocusable = true
        toFrame7Btn?.bringToFront()
        toFrame7Btn?.setOnClickListener {
            Log.d("Frame6Activity", "btnToFrame7 clicked, launching Frame7Activity")
            startActivity(Intent(this, Frame7Activity::class.java))
        }

        // Tombol untuk membuka Frame8Activity
        val toFrame8Btn: ImageButton? = findViewById(R.id.btnToFrame8)
        Log.d("Frame6Activity", "btnToFrame8 found = ${toFrame8Btn != null}")
        toFrame8Btn?.isClickable = true
        toFrame8Btn?.isFocusable = true
        toFrame8Btn?.bringToFront()
        toFrame8Btn?.setOnClickListener {
            Log.d("Frame6Activity", "btnToFrame8 clicked, launching Frame8Activity")
            startActivity(Intent(this, Frame8Activity::class.java))
        }

        // Tombol untuk membuka Frame9Activity
        val toFrame9Btn: ImageButton? = findViewById(R.id.btnToFrame9)
        Log.d("Frame6Activity", "btnToFrame9 found = ${toFrame9Btn != null}")
        toFrame9Btn?.isClickable = true
        toFrame9Btn?.isFocusable = true
        toFrame9Btn?.bringToFront()
        toFrame9Btn?.setOnClickListener {
            Log.d("Frame6Activity", "btnToFrame9 clicked, launching Frame9Activity")
            startActivity(Intent(this, Frame9Activity::class.java))
        }

        // Tombol untuk membuka Frame10Activity
        val toFrame10Btn: ImageButton? = findViewById(R.id.btnToFrame10)
        Log.d("Frame6Activity", "btnToFrame10 found = ${toFrame10Btn != null}")
        toFrame10Btn?.isClickable = true
        toFrame10Btn?.isFocusable = true
        toFrame10Btn?.bringToFront()
        toFrame10Btn?.setOnClickListener {
            Log.d("Frame6Activity", "btnToFrame10 clicked, launching Frame10Activity")
            startActivity(Intent(this, Frame10Activity::class.java))
        }

        // Tombol untuk membuka Frame11Activity
        val toFrame11Btn: ImageButton? = findViewById(R.id.btnToFrame11)
        Log.d("Frame6Activity", "btnToFrame11 found = ${toFrame11Btn != null}")
        toFrame11Btn?.isClickable = true
        toFrame11Btn?.isFocusable = true
        toFrame11Btn?.bringToFront()
        toFrame11Btn?.setOnClickListener {
            Log.d("Frame6Activity", "btnToFrame11 clicked, launching Frame11Activity")
            startActivity(Intent(this, Frame11Activity::class.java))
        }

        // Tombol rumah: kembali ke tampilan utama (MainActivity / activity_main.xml)
        findViewById<ImageButton>(R.id.btnHome)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
