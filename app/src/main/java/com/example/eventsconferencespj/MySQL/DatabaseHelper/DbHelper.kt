package com.example.eventsconferencespj.MySQL.DatabaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        const val DATABASE_NAME = "login.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, name TEXT, phone INTEGER DEFAULT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    fun Insert(email: String?, password: String?): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)
        val result = sqLiteDatabase.insert("user", null, contentValues)
        sqLiteDatabase.close()
        return result != -1L
    }

    fun InsertPhone(phone: Int): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("phone", phone)
        val result = sqLiteDatabase.insert("user", null, contentValues)
        sqLiteDatabase.close()
        return result != -1L
    }
    fun getPhoneNumberFromDatabase(): Int? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT phone FROM user" // Chọn số điện thoại từ bảng user
        val cursor = sqLiteDatabase.rawQuery(query, null)
        val phoneNumber: Int?
        if (cursor.moveToFirst()) {
            phoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow("phone"))
        } else {
            phoneNumber = null
        }
        cursor.close()
        sqLiteDatabase.close()
        return phoneNumber
    }

    fun CheckEmail(email: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", arrayOf(email))
        val isEmailExists = cursor.count > 0
        cursor.close()
        sqLiteDatabase.close()
        return !isEmailExists
    }

    fun CheckLogin(email: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=? AND password=?", arrayOf(email, password))
        val isLoggedIn = cursor.count > 0
        cursor.close()
        sqLiteDatabase.close()
        return isLoggedIn
    }
}
