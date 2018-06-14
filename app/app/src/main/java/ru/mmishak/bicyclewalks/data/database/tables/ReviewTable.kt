package ru.mmishak.bicyclewalks.data.database.tables

import android.provider.BaseColumns

object ReviewTable {
    const val TABLE_NAME = "reviews"

    const val _ID = BaseColumns._ID
    const val COLUMN_TEXT = "text_column"
    const val COLUMN_AUTHOR_ID = "author_id"
    const val COLUMN_WALK_ID = "walk_id"
    const val COLUMN_TIME = "time"

    val SQL_CREATE_TABLE: String
        get() = "CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TEXT TEXT NOT NULL, $COLUMN_AUTHOR_ID INTEGER NOT NULL, " +
                "$COLUMN_WALK_ID INTEGER NOT NULL, $COLUMN_TIME INTEGER NOT NULL, " +
                "FOREIGN KEY($COLUMN_AUTHOR_ID) REFERENCES ${CyclistTable.TABLE_NAME}(${CyclistTable._ID}), " +
                "FOREIGN KEY($COLUMN_WALK_ID) REFERENCES ${BicycleWalkTable.TABLE_NAME}(${BicycleWalkTable._ID}));"

    val SQL_DROP_TABLE: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"
}