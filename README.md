# 🎮 PETUALANGAN ILMU BARENG LAROS

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

## 📋 Informasi Mahasiswa

| Atribut | Keterangan |
|---------|------------|
| **Nama** | Muhammad Rizal Nurfirdaus |
| **NIM** | 20230810088 |
| **Kelas** | TINFC-2023-04 |
| **Mata Kuliah** | Bahasa Pemrograman 3 |
| **Dosen Pengampu** | Rio Andriyat Krisdiawan, M.Kom |

---

## 📖 Tentang Project

**Petualangan Ilmu bareng Laros** adalah aplikasi Android edukatif berbasis cerita interaktif yang dirancang untuk memberikan pengalaman belajar yang menyenangkan. Aplikasi ini menggabungkan unsur petualangan dan edukasi dengan karakter utama bernama **Laros** yang menemani pengguna dalam perjalanan belajar.

### ✨ Fitur Utama

- 🎯 **Cerita Interaktif** - 22 frame aktivitas dengan alur cerita yang menarik
- 👤 **Profil Pengguna** - Sistem profil untuk personalisasi pengalaman
- 📝 **Kuis Edukatif** - Fitur kuis untuk menguji pemahaman pengguna
- 🔐 **Keamanan Data** - Implementasi keamanan untuk melindungi data pengguna
- 💾 **Database Lokal** - Penyimpanan data pengguna dengan SQLite

### 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman:** Kotlin
- **Platform:** Android (Min SDK 27, Target SDK 36)
- **Build System:** Gradle dengan Kotlin DSL
- **Database:** SQLite (UserDbHelper)
- **UI Components:** Material Design, ConstraintLayout

### 📁 Struktur Project

```
app/src/main/
├── java/com/pab/petualangan_ilmu_bareng_laros/
│   ├── MainActivity.kt          # Activity utama
│   ├── ProfileActivity.kt       # Activity profil pengguna
│   ├── QuizActivity.kt          # Activity kuis
│   ├── Frame1Activity.kt        # Frame cerita 1
│   ├── Frame2Activity.kt        # Frame cerita 2
│   ├── ... (sampai Frame22)     # Frame cerita lainnya
│   ├── SecurityUtils.kt         # Utilitas keamanan
│   └── UserDbHelper.kt          # Helper database pengguna
└── res/
    ├── layout/                   # File XML layout
    ├── drawable/                 # Gambar dan icon
    ├── font/                     # Custom font
    ├── values/                   # String, colors, themes
    └── mipmap/                   # App icons
```

---

## 🚀 Cara Menjalankan Project

### Prasyarat
- Android Studio (versi terbaru)
- JDK 11 atau lebih tinggi
- Android SDK

### Langkah-langkah

1. **Clone repository**
   ```bash
   git clone https://github.com/MuhammadRizalNurfirdaus/Petualangan_Ilmu_bareng_Laros.git
   ```

2. **Buka project di Android Studio**
   ```
   File > Open > Pilih folder project
   ```

3. **Sync Gradle**
   ```
   Tunggu proses sync selesai
   ```

4. **Jalankan aplikasi**
   ```
   Run > Run 'app' atau tekan Shift + F10
   ```

---

## 🔒 Keamanan

File-file sensitif berikut **TIDAK** akan di-commit ke repository:
- `local.properties` - Berisi path SDK lokal
- `.idea/` - Konfigurasi IDE
- `*.jks` / `*.keystore` - File signing key
- File konfigurasi dengan API keys atau credentials

> ⚠️ **Penting:** Pastikan untuk tidak menambahkan informasi sensitif seperti API keys, passwords, atau credentials ke dalam repository.

---

## 📱 Screenshots

*Coming Soon*

---

## 📄 Lisensi

Project ini dibuat untuk keperluan tugas mata kuliah Bahasa Pemrograman 3.

---

## 🔗 Link Repository

[![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github)](https://github.com/MuhammadRizalNurfirdaus/Petualangan_Ilmu_bareng_Laros.git)

**Repository:** [https://github.com/MuhammadRizalNurfirdaus/Petualangan_Ilmu_bareng_Laros.git](https://github.com/MuhammadRizalNurfirdaus/Petualangan_Ilmu_bareng_Laros.git)

---

<p align="center">
  Made with ❤️ by Muhammad Rizal Nurfirdaus
</p>
