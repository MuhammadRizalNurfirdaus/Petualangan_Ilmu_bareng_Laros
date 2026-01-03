package com.pab.petualangan_ilmu_bareng_laros

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE_USERS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USERNAME TEXT NOT NULL UNIQUE,
                $COL_PASSWORD_HASH TEXT NOT NULL,
                $COL_SALT TEXT NOT NULL,
                $COL_PHOTO_URI TEXT,
                $COL_CREATED_AT INTEGER NOT NULL
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun userExists(username: String): Boolean {
        readableDatabase.use { db ->
            db.query(
                TABLE_USERS,
                arrayOf(COL_ID),
                "$COL_USERNAME = ?",
                arrayOf(username),
                null, null, null
            ).use { c ->
                return c.moveToFirst()
            }
        }
    }

    fun insertUser(username: String, password: String, photoUri: String?): Long {
        val salt = SecurityUtils.generateSalt()
        val hash = SecurityUtils.hashPassword(password, salt)

        val values = ContentValues().apply {
            put(COL_USERNAME, username)
            put(COL_PASSWORD_HASH, hash)
            put(COL_SALT, salt)
            put(COL_PHOTO_URI, photoUri)
            put(COL_CREATED_AT, System.currentTimeMillis())
        }
        writableDatabase.use { db ->
            return db.insert(TABLE_USERS, null, values)
        }
    }

    fun verifyUser(username: String, password: String): Boolean {
        readableDatabase.use { db ->
            db.query(
                TABLE_USERS,
                arrayOf(COL_PASSWORD_HASH, COL_SALT),
                "$COL_USERNAME = ?",
                arrayOf(username),
                null, null, null
            ).use { c ->
                if (!c.moveToFirst()) return false
                val storedHash = c.getString(0)
                val salt = c.getString(1)
                val attemptHash = SecurityUtils.hashPassword(password, salt)
                return storedHash == attemptHash
            }
        }
    }

    companion object {
        private const val DATABASE_NAME = "users.db"
    private const val DATABASE_VERSION = 2

        const val TABLE_USERS = "users"
        const val COL_ID = "id"
        const val COL_USERNAME = "username"
    const val COL_PASSWORD_HASH = "password_hash"
    const val COL_SALT = "salt"
        const val COL_PHOTO_URI = "photo_uri"
        const val COL_CREATED_AT = "created_at"
    }
}
