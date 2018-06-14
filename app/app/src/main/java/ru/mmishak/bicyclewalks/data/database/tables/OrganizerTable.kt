package ru.mmishak.bicyclewalks.data.database.tables

import android.provider.BaseColumns

object OrganizerTable {
    const val TABLE_NAME = "organizers"

    const val _ID = BaseColumns._ID
    const val COLUMN_LOGIN = "login"
    const val COLUMN_PASSWORD = "password"
    const val COLUMN_EMAIL = "email"
    const val COLUMN_TITLE = "title"

    val SQL_CREATE_TABLE: String
        get() = "CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_LOGIN TEXT NOT NULL, $COLUMN_PASSWORD TEXT NOT NULL, " +
                "$COLUMN_EMAIL TEXT NOT NULL, $COLUMN_TITLE TEXT NOT NULL);"

    val SQL_DROP_TABLE: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"
}