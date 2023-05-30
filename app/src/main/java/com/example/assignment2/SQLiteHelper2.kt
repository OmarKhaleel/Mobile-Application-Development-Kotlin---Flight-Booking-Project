package com.example.assignment2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper2(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table tickets(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "fullName TEXT NOT NULL, departure TEXT NOT NULL, destination TEXT NOT NULL, "
                + "departureDateTime TEXT NOT NULL, flightClass TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS tickets")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FlightDetails"
    }

    fun insertTicket(fullName: String, departure: String, destination: String, departureDateTime: String, flightClass: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("fullName", fullName)
        contentValues.put("departure", departure)
        contentValues.put("destination", destination)
        contentValues.put("departureDateTime", departureDateTime)
        contentValues.put("flightClass", flightClass)
        val success = db.insert("tickets", null, contentValues)
        db.close()

        return success
    }

    fun getAllTickets(): ArrayList<TicketModel> {
        val ticketsList: ArrayList<TicketModel> = ArrayList()
        val selectQuery= "SELECT * FROM tickets"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var fullName: String
        var departure: String
        var destination: String
        var departureDateTime: String
        var flightClass: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullName"))
                departure = cursor.getString(cursor.getColumnIndexOrThrow("departure"))
                destination = cursor.getString(cursor.getColumnIndexOrThrow("destination"))
                departureDateTime = cursor.getString(cursor.getColumnIndexOrThrow("departureDateTime"))
                flightClass = cursor.getString(cursor.getColumnIndexOrThrow("flightClass"))

                val ticket = TicketModel(id, fullName, departure, destination, departureDateTime, flightClass)
                ticketsList.add(ticket)
            } while (cursor.moveToNext())
        }
        cursor.close()

        return ticketsList
    }

    fun updateTicket(ticket: TicketModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("id", ticket.id)
        contentValues.put("fullName", ticket.fullName)
        contentValues.put("departure", ticket.departure)
        contentValues.put("destination", ticket.destination)
        contentValues.put("departureDateTime", ticket.departureDateTime)
        contentValues.put("flightClass", ticket.flightClass)

        val success = db.update("tickets", contentValues, "id=" + ticket.id, null)
        db.close()

        return success
    }

    fun deleteTicketById(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("id", id)

        val success = db.delete("tickets", "id=$id", null)
        db.close()

        return success
    }
}