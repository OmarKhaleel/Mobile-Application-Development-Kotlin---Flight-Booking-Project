package com.example.assignment2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table usersDetails(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT NOT NULL, email TEXT NOT NULL UNIQUE, password TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS usersDetails")
        onCreate(db)
    }

    fun insertUserData(fullName: String, email: String, password: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("fullName", fullName)
        contentValues.put("email", email)
        contentValues.put("password", password)

        db.insert("usersDetails", null, contentValues)
        db.close()
    }

    fun userRegistered(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "select * from usersDetails where email = '$email' and password = '$password'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }

        cursor.close()
        return true
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Users"
    }
}