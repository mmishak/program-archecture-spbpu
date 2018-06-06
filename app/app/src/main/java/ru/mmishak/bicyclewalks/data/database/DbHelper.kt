package ru.mmishak.bicyclewalks.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.mmishak.bicyclewalks.data.database.tables.*

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return

        db.execSQL(BicycleWalkTable.SQL_CREATE_TABLE)
        db.execSQL(CyclistTable.SQL_CREATE_TABLE)
        db.execSQL(LeaderTable.SQL_CREATE_TABLE)
        db.execSQL(OrganizerTable.SQL_CREATE_TABLE)
        db.execSQL(ReviewTable.SQL_CREATE_TABLE)
        db.execSQL(CyclistWalkTable.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db ?: return

        db.execSQL(BicycleWalkTable.SQL_DROP_TABLE)
        db.execSQL(CyclistTable.SQL_DROP_TABLE)
        db.execSQL(LeaderTable.SQL_DROP_TABLE)
        db.execSQL(OrganizerTable.SQL_DROP_TABLE)
        db.execSQL(ReviewTable.SQL_DROP_TABLE)
        db.execSQL(CyclistWalkTable.SQL_DROP_TABLE)

        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "bicycles.db"
    }
}