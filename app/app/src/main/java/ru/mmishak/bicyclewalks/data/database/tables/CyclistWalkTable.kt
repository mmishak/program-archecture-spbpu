package ru.mmishak.bicyclewalks.data.database.tables

import android.provider.BaseColumns

object CyclistWalkTable {
    const val TABLE_NAME = "cyclists_walks"

    const val COLUMN_CYCLIST_ID = "cyclist_id"
    const val COLUMN_WALK_ID = "walk_id"

    val SQL_CREATE_TABLE: String
        get() = "CREATE TABLE $TABLE_NAME ($COLUMN_CYCLIST_ID INTEGER NOT NULL, $COLUMN_WALK_ID INTEGER NOT NULL, " +
                "FOREIGN KEY($COLUMN_CYCLIST_ID) REFERENCES ${CyclistTable.TABLE_NAME}(${CyclistTable._ID}), " +
                "FOREIGN KEY($COLUMN_WALK_ID) REFERENCES ${BicycleWalkTable.TABLE_NAME}(${BicycleWalkTable._ID}));"

    val SQL_DROP_TABLE: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"
}