package com.pab.petualangan_ilmu_bareng_laros

import android.os.Bundle
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // MainActivity sekarang menampilkan activity_main (frame_7) dan logika mengenal hewan
        setContentView(R.layout.activity_main)

        // Tombol Mengenal Hewan (klik area di desain frame_7)
        val mengenalHewanBtn: ImageButton? = findViewById(R.id.btnMengenalHewan)
        mengenalHewanBtn?.setOnClickListener {
            startActivity(Intent(this, Frame5Activity::class.java))
        }

        val mengenalTataSuryaBtn: ImageButton? = findViewById(R.id.btnMengenalTataSurya)
        mengenalTataSuryaBtn?.setOnClickListener {
            startActivity(Intent(this, Frame12Activity::class.java))
        }

        val quizBtn: ImageButton? = findViewById(R.id.btnQuiz)
        quizBtn?.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Tombol Exit dengan dialog konfirmasi
        val exitBtn: ImageButton? = findViewById(R.id.btnExit)
        exitBtn?.setOnClickListener {
            showExitConfirmationDialog()
        }

        // Tombol Profile
        val profileBtn: ImageButton? = findViewById(R.id.btnProfile)
        profileBtn?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                finishAffinity() // Menutup semua activity dan keluar dari aplikasi
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Menutup dialog tanpa keluar
            }
            .setCancelable(true)
            .show()
    }
}