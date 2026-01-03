package com.pab.petualangan_ilmu_bareng_laros

import android.animation.ObjectAnimator
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val category: String
)

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuizTitle: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvTimer: TextView
    private lateinit var progressTimer: ProgressBar
    private lateinit var btnOption1: Button
    private lateinit var btnOption2: Button
    private lateinit var btnOption3: Button
    private lateinit var btnOption4: Button
    private lateinit var btnHomeQuiz: ImageButton

    private val optionButtons = mutableListOf<Button>()
    private var currentQuestionIndex = 0
    private var score = 0
    private var isAnswered = false
    private var countDownTimer: CountDownTimer? = null
    private val timerDuration = 12000L // 12 detik dalam milidetik

    // Daftar pertanyaan yang sudah diacak dan dibatasi 10 soal
    private lateinit var shuffledQuestions: List<Question>

    private val questions = listOf(
        // Pertanyaan tentang Hewan
        Question(
            "Hewan apakah yang memiliki leher paling panjang?",
            listOf("Kuda Nil", "Singa", "Jerapah", "Kuda"),
            2,
            "Hewan"
        ),
        Question(
            "Hewan apakah yang dikenal sebagai raja hutan?",
            listOf("Harimau", "Singa", "Jerapah", "Kuda"),
            1,
            "Hewan"
        ),
        Question(
            "Hewan apakah yang memiliki belang hitam dan orange?",
            listOf("Singa", "Kuda", "Harimau", "Jerapah"),
            2,
            "Hewan"
        ),
        Question(
            "Hewan apakah yang suka hidup di air dan darat?",
            listOf("Kuda Nil", "Kuda", "Jerapah", "Harimau"),
            0,
            "Hewan"
        ),
        Question(
            "Hewan apakah yang bisa berlari sangat cepat dan suka makan rumput?",
            listOf("Jerapah", "Singa", "Kuda", "Kuda Nil"),
            2,
            "Hewan"
        ),

        // Pertanyaan tentang Tata Surya
        Question(
            "Planet apakah yang paling dekat dengan Matahari?",
            listOf("Venus", "Merkurius", "Bumi", "Mars"),
            1,
            "Tata Surya"
        ),
        Question(
            "Benda langit apakah yang memberikan cahaya kepada Bumi?",
            listOf("Bulan", "Mars", "Matahari", "Venus"),
            2,
            "Tata Surya"
        ),
        Question(
            "Planet apakah yang kita tinggali?",
            listOf("Mars", "Venus", "Bumi", "Jupiter"),
            2,
            "Tata Surya"
        ),
        Question(
            "Planet apakah yang memiliki cincin yang indah?",
            listOf("Jupiter", "Saturnus", "Uranus", "Neptunus"),
            1,
            "Tata Surya"
        ),
        Question(
            "Planet apakah yang berwarna merah?",
            listOf("Mars", "Venus", "Merkurius", "Jupiter"),
            0,
            "Tata Surya"
        ),
        Question(
            "Satelit alami Bumi adalah?",
            listOf("Matahari", "Mars", "Bulan", "Venus"),
            2,
            "Tata Surya"
        ),
        Question(
            "Planet terbesar di Tata Surya adalah?",
            listOf("Saturnus", "Jupiter", "Uranus", "Neptunus"),
            1,
            "Tata Surya"
        ),
        Question(
            "Planet apakah yang berwarna biru kehijauan?",
            listOf("Neptunus", "Uranus", "Bumi", "Mars"),
            1,
            "Tata Surya"
        ),
        Question(
            "Planet apakah yang dikenal sebagai bintang kejora?",
            listOf("Merkurius", "Venus", "Mars", "Jupiter"),
            1,
            "Tata Surya"
        ),
        Question(
            "Planet terjauh dari Matahari adalah?",
            listOf("Uranus", "Saturnus", "Neptunus", "Jupiter"),
            2,
            "Tata Surya"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Set orientation ke landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        
        setContentView(R.layout.activity_quiz)

        initializeViews()
        setupListeners()

        // Acak urutan pertanyaan dan ambil hanya 10 soal
        shuffledQuestions = questions.shuffled().take(10)

        loadQuestion()
    }

    private fun initializeViews() {
        tvQuizTitle = findViewById(R.id.tvQuizTitle)
        tvCategory = findViewById(R.id.tvCategory)
        tvScore = findViewById(R.id.tvScore)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvTimer = findViewById(R.id.tvTimer)
        progressTimer = findViewById(R.id.progressTimer)
        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)
        btnHomeQuiz = findViewById(R.id.btnHomeQuiz)

        optionButtons.addAll(listOf(btnOption1, btnOption2, btnOption3, btnOption4))
    }

    private fun setupListeners() {
        btnHomeQuiz.setOnClickListener {
            showExitConfirmation()
        }

        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!isAnswered) {
                    stopTimer()
                    checkAnswer(index)
                }
            }
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < shuffledQuestions.size) {
            isAnswered = false
            val currentQuestion = shuffledQuestions[currentQuestionIndex]

            tvQuestionNumber.text = "Pertanyaan ${currentQuestionIndex + 1} dari ${shuffledQuestions.size}"
            tvQuestion.text = currentQuestion.question
            tvCategory.text = "Kategori: ${currentQuestion.category}"

            // Reset warna tombol
            val defaultColors = listOf("#7E57C2", "#5C6BC0", "#42A5F5", "#26C6DA")
            currentQuestion.options.forEachIndexed { index, option ->
                optionButtons[index].text = option
                optionButtons[index].setBackgroundColor(Color.parseColor(defaultColors[index]))
                optionButtons[index].isEnabled = true
            }

            // Mulai timer countdown
            startTimer()
        } else {
            showFinalScore()
        }
    }

    private fun startTimer() {
        // Reset progress bar
        progressTimer.progress = 12
        tvTimer.text = "12"
        tvTimer.setTextColor(Color.parseColor("#D84315"))
        
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                tvTimer.text = secondsRemaining.toString()
                
                // Animasi progress bar
                val animator = ObjectAnimator.ofInt(progressTimer, "progress", secondsRemaining)
                animator.duration = 1000
                animator.interpolator = DecelerateInterpolator()
                animator.start()
                
                // Ubah warna jadi merah jika waktu tinggal sedikit
                when {
                    secondsRemaining <= 3 -> {
                        tvTimer.setTextColor(Color.parseColor("#C62828"))
                        // Animasi pulse pada timer
                        tvTimer.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setDuration(500)
                            .withEndAction {
                                tvTimer.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(500)
                                    .start()
                            }
                            .start()
                    }
                    secondsRemaining <= 6 -> {
                        tvTimer.setTextColor(Color.parseColor("#F57C00"))
                    }
                    else -> {
                        tvTimer.setTextColor(Color.parseColor("#D84315"))
                    }
                }
            }

            override fun onFinish() {
                if (!isAnswered) {
                    // Waktu habis, otomatis pindah ke soal berikutnya
                    tvTimer.text = "0"
                    tvTimer.setTextColor(Color.parseColor("#C62828"))
                    isAnswered = true
                    
                    // Disable semua tombol
                    optionButtons.forEach { it.isEnabled = false }
                    
                    // Tampilkan jawaban yang benar
                    val currentQuestion = shuffledQuestions[currentQuestionIndex]
                    optionButtons[currentQuestion.correctAnswerIndex].setBackgroundColor(Color.parseColor("#4CAF50"))
                    
                    // Pindah ke pertanyaan berikutnya setelah 2 detik
                    Handler(Looper.getMainLooper()).postDelayed({
                        currentQuestionIndex++
                        loadQuestion()
                    }, 2000)
                }
            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

    private fun checkAnswer(selectedIndex: Int) {
        isAnswered = true
        val currentQuestion = shuffledQuestions[currentQuestionIndex]

        if (selectedIndex == currentQuestion.correctAnswerIndex) {
            // Jawaban benar
            score += 10
            optionButtons[selectedIndex].setBackgroundColor(Color.parseColor("#4CAF50"))
            tvScore.text = "Skor: $score"
        } else {
            // Jawaban salah
            optionButtons[selectedIndex].setBackgroundColor(Color.parseColor("#F44336"))
            // Tampilkan jawaban yang benar
            optionButtons[currentQuestion.correctAnswerIndex].setBackgroundColor(Color.parseColor("#4CAF50"))
        }

        // Disable semua tombol
        optionButtons.forEach { it.isEnabled = false }

        // Pindah ke pertanyaan berikutnya setelah 2 detik
        Handler(Looper.getMainLooper()).postDelayed({
            currentQuestionIndex++
            loadQuestion()
        }, 2000)
    }

    private fun showFinalScore() {
        val totalQuestions = shuffledQuestions.size
        val maxScore = totalQuestions * 10
        val percentage = (score.toFloat() / maxScore * 100).toInt()

        val message = """
            Selamat! Kamu telah menyelesaikan kuis!
            
            Skor Akhir: $score dari $maxScore
            Persentase: $percentage%
            Benar: ${score / 10} dari $totalQuestions pertanyaan
            
            ${when {
                percentage >= 90 -> "Luar biasa! Kamu sangat pintar! 🌟"
                percentage >= 70 -> "Bagus sekali! Terus belajar ya! 👍"
                percentage >= 50 -> "Cukup baik! Ayo belajar lagi! 📚"
                else -> "Jangan menyerah! Coba lagi ya! 💪"
            }}
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Kuis Selesai!")
            .setMessage(message)
            .setPositiveButton("Main Lagi") { _, _ ->
                restartQuiz()
            }
            .setNegativeButton("Kembali ke Menu") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        tvScore.text = "Skor: 0"
        // Acak ulang pertanyaan dan ambil 10 soal baru
        shuffledQuestions = questions.shuffled().take(10)
        loadQuestion()
    }

    private fun showExitConfirmation() {
        stopTimer()
        AlertDialog.Builder(this)
            .setTitle("🏠 Kembali ke Menu Utama")
            .setMessage("Apakah kamu yakin ingin kembali ke menu utama?\n\nSkor kamu saat ini: $score\nProgress kamu tidak akan disimpan.")
            .setPositiveButton("Ya, Kembali") { _, _ ->
                finish()
            }
            .setNegativeButton("Tidak, Lanjut Quiz") { _, _ ->
                if (!isAnswered && currentQuestionIndex < shuffledQuestions.size) {
                    startTimer()
                }
            }
            .setCancelable(false)
            .show()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        showExitConfirmation()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}

