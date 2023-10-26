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

    fun updatePhone(email: String, newPhone: Int): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("phone", newPhone)

        // Cập nhật số điện thoại dựa trên email
        val updatedRows = sqLiteDatabase.update(
            "user",
            contentValues,
            "email = ?",
            arrayOf(email)
        )

        sqLiteDatabase.close()
        // Kiểm tra xem có dòng nào đã được cập nhật không
        return updatedRows > 0
    }
    fun UpdateName(email: String, name: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name) // Cập nhật tên dựa trên email
        val updatedRows = sqLiteDatabase.update(
            "user",
            contentValues,
            "email = ?",
            arrayOf(email)
        )
        sqLiteDatabase.close()
        // Kiểm tra xem có dòng nào đã được cập nhật không
        return updatedRows > 0
    }

//    // hàm tối ưu cho insert name, phone, email, pass
//    fun insertUserData(email: String?, password: String?, phone: Int?, name: String?): Boolean {
//        val sqLiteDatabase = this.writableDatabase
//        val contentValues = ContentValues()
//
//        email?.let { contentValues.put("email", it) }
//        password?.let { contentValues.put("password", it) }
//        phone?.let { contentValues.put("phone", it) }
//        name?.let { contentValues.put("name", it) }
//
//        val result = sqLiteDatabase.insert("user", null, contentValues)
//        sqLiteDatabase.close()
//
//        return result != -1L
//    }

    fun getPhoneNum(email: String): Int? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT phone FROM user WHERE email = ?"
        val cursor = sqLiteDatabase.rawQuery(query, arrayOf(email))
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

    fun getName(email: String): String? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT name FROM user WHERE email = ?"
        val cursor = sqLiteDatabase.rawQuery(query, arrayOf(email))
        val name: String?

        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
        } else {
            name = null
        }

        cursor.close()
        sqLiteDatabase.close()
        return name
    }

    fun getEmail(email: String): String? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT email FROM user WHERE email = ?"
        val cursor = sqLiteDatabase.rawQuery(query, arrayOf(email))
        val result: String?

        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndexOrThrow("email"))
        } else {
            result = null
        }

        cursor.close()
        sqLiteDatabase.close()
        return result
    }

    fun getPassword(email: String): String? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT password FROM user WHERE email = ?"
        val cursor = sqLiteDatabase.rawQuery(query, arrayOf(email))
        val result: String?

        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndexOrThrow("password"))
        } else {
            result = null
        }

        cursor.close()
        sqLiteDatabase.close()
        return result
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
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM user WHERE email=? AND password=?",
            arrayOf(email, password)
        )
        val isLoggedIn = cursor.count > 0
        cursor.close()
        sqLiteDatabase.close()
        return isLoggedIn
    }

    fun updatePassword(email: String, newPassword: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("password", newPassword)

        // Cập nhật mật khẩu dựa trên email
        val updatedRows = sqLiteDatabase.update(
            "user",
            contentValues,
            "email = ?",
            arrayOf(email)
        )

        sqLiteDatabase.close()
        // Kiểm tra xem có dòng nào đã được cập nhật không
        return updatedRows > 0
    }

}
