package com.pab.petualangan_ilmu_bareng_laros

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity() {

    private lateinit var ivProfilePicture: ImageView
    private lateinit var btnEditPhoto: ImageButton
    private lateinit var btnRemovePhoto: Button
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEditUsername: ImageButton
    private lateinit var btnEditPassword: ImageButton
    private lateinit var btnTogglePassword: ImageButton
    private lateinit var btnSave: Button
    private lateinit var btnHome: ImageButton

    private lateinit var sharedPreferences: SharedPreferences
    private var isPasswordVisible = false
    private var isEditingUsername = false
    private var isEditingPassword = false

    // Launcher untuk memilih gambar dari galeri
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { uri ->
                loadImageFromUri(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        setupSharedPreferences()
        loadProfileData()
        setupListeners()
    }

    private fun initViews() {
        ivProfilePicture = findViewById(R.id.ivProfilePicture)
        btnEditPhoto = findViewById(R.id.btnEditPhoto)
        btnRemovePhoto = findViewById(R.id.btnRemovePhoto)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnEditUsername = findViewById(R.id.btnEditUsername)
        btnEditPassword = findViewById(R.id.btnEditPassword)
        btnTogglePassword = findViewById(R.id.btnTogglePassword)
        btnSave = findViewById(R.id.btnSave)
        btnHome = findViewById(R.id.btnHome)
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
    }

    private fun loadProfileData() {
        // Load username
        val username = sharedPreferences.getString("username", "")
        etUsername.setText(username)

        // Load password
        val password = sharedPreferences.getString("password", "")
        etPassword.setText(password)

        // Load profile picture
        val imageString = sharedPreferences.getString("profile_picture", null)
        if (imageString != null) {
            val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            ivProfilePicture.setImageBitmap(bitmap)
        }
    }

    private fun setupListeners() {
        // Home button
        btnHome.setOnClickListener {
            finish() // Kembali ke MainActivity
        }

        // Edit photo button
        btnEditPhoto.setOnClickListener {
            showImagePickerDialog()
        }

        // Remove photo button
        btnRemovePhoto.setOnClickListener {
            removeProfilePicture()
        }

        // Edit username button
        btnEditUsername.setOnClickListener {
            toggleUsernameEdit()
        }

        // Edit password button
        btnEditPassword.setOnClickListener {
            togglePasswordEdit()
        }

        // Toggle password visibility
        btnTogglePassword.setOnClickListener {
            togglePasswordVisibility()
        }

        // Save button
        btnSave.setOnClickListener {
            saveProfileData()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Pilih dari Galeri", "Batal")
        AlertDialog.Builder(this)
            .setTitle("Ubah Foto Profil")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> pickImageFromGallery()
                    1 -> dialog.dismiss()
                }
            }
            .show()
    }

    private fun pickImageFromGallery() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                PERMISSION_REQUEST_CODE
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }
    }

    private fun loadImageFromUri(uri: Uri) {
        try {
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            ivProfilePicture.setImageBitmap(bitmap)
            
            // Simpan gambar ke SharedPreferences
            saveImageToPreferences(bitmap)
            Toast.makeText(this, "Foto profil berhasil diubah", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImageToPreferences(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)
        
        sharedPreferences.edit().putString("profile_picture", encodedImage).apply()
    }

    private fun removeProfilePicture() {
        AlertDialog.Builder(this)
            .setTitle("Hapus Foto Profil")
            .setMessage("Apakah Anda yakin ingin menghapus foto profil?")
            .setPositiveButton("Ya") { _, _ ->
                ivProfilePicture.setImageResource(android.R.drawable.ic_menu_gallery)
                sharedPreferences.edit().remove("profile_picture").apply()
                Toast.makeText(this, "Foto profil berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun toggleUsernameEdit() {
        isEditingUsername = !isEditingUsername
        etUsername.isEnabled = isEditingUsername
        
        if (isEditingUsername) {
            etUsername.requestFocus()
            btnEditUsername.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
        } else {
            etUsername.clearFocus()
            btnEditUsername.setImageResource(android.R.drawable.ic_menu_edit)
        }
        
        updateSaveButtonVisibility()
    }

    private fun togglePasswordEdit() {
        isEditingPassword = !isEditingPassword
        etPassword.isEnabled = isEditingPassword
        
        if (isEditingPassword) {
            etPassword.requestFocus()
            btnEditPassword.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
        } else {
            etPassword.clearFocus()
            btnEditPassword.setImageResource(android.R.drawable.ic_menu_edit)
        }
        
        updateSaveButtonVisibility()
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        
        if (isPasswordVisible) {
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            btnTogglePassword.setImageResource(android.R.drawable.ic_menu_view)
        } else {
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            btnTogglePassword.setImageResource(android.R.drawable.ic_menu_view)
        }
        
        // Set cursor ke akhir teks
        etPassword.setSelection(etPassword.text.length)
    }

    private fun updateSaveButtonVisibility() {
        btnSave.visibility = if (isEditingUsername || isEditingPassword) {
            android.view.View.VISIBLE
        } else {
            android.view.View.GONE
        }
    }

    private fun saveProfileData() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // Validasi username
        if (isEditingUsername && username.isEmpty()) {
            Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
            etUsername.requestFocus()
            return
        }

        // Validasi password
        if (isEditingPassword && password.isEmpty()) {
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            etPassword.requestFocus()
            return
        }

        // Simpan data
        val editor = sharedPreferences.edit()
        if (isEditingUsername) {
            editor.putString("username", username)
        }
        if (isEditingPassword) {
            editor.putString("password", password)
        }
        editor.apply()

        // Reset editing state
        if (isEditingUsername) {
            toggleUsernameEdit()
        }
        if (isEditingPassword) {
            togglePasswordEdit()
        }

        Toast.makeText(this, "Profil berhasil disimpan", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery()
            } else {
                Toast.makeText(this, "Izin diperlukan untuk memilih gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}

