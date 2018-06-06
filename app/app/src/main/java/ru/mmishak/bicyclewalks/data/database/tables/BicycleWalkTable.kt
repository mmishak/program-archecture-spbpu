package ru.mmishak.bicyclewalks.data.database.tables

import android.provider.BaseColumns

object BicycleWalkTable {
    const val TABLE_NAME = "bicycle_walks"

    const val _ID = BaseColumns._ID
    const val COLUMN_TITLE = "title"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_WALK_TYPE = "walk_type"
    const val COLUMN_DURATION = "duration"
    const val COLUMN_DISTANCE = "distance"
    const val COLUMN_DATE = "date"
    const val COLUMN_PRICE = "price"
    const val COLUMN_PAYMENT_TYPE = "payment_type"
    const val COLUMN_ORGANIZER_ID = "organizer_id"
    const val COLUMN_LEADER_ID = "leader_id"
    const val COLUMN_LEADER_STATUS = "leader_status"

    const val TYPE_WALK = 0
    const val TYPE_EXCURSION = 1

    const val TYPE_PAY = 0
    const val TYPE_FREE = 1

    const val STATUS_WITHOUT_LEADER = 0
    const val STATUS_WAITING_ACCEPT = 1
    const val STATUS_ACCEPTED = 2
    const val STATUS_REJECTE = 3

    val SQL_CREATE_TABLE: String
        get() = "CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT NOT NULL, $COLUMN_DESCRIPTION TEXT NOT NULL, " +
                "$COLUMN_WALK_TYPE INTEGER NOT NULL DEFAULT $TYPE_WALK, " +
                "$COLUMN_DURATION INTEGER NOT NULL DEFAULT 0, " +
                "$COLUMN_DISTANCE INTEGER NOT NULL DEFAULT 0, " +
                "$COLUMN_DATE TEXT NOT NULL, " +
                "$COLUMN_PRICE INTEGER NOT NULL DEFAULT 0, " +
                "$COLUMN_PAYMENT_TYPE INTEGER NOT NULL DEFAULT $TYPE_FREE, " +
                "$COLUMN_ORGANIZER_ID INTEGER NOT NULL, " +
                "$COLUMN_LEADER_ID INTEGER, " +
                "$COLUMN_LEADER_STATUS INTEGER NOT NULL DEFAULT $STATUS_WITHOUT_LEADER, " +
                "FOREIGN KEY($COLUMN_ORGANIZER_ID) REFERENCES ${OrganizerTable.TABLE_NAME}(${OrganizerTable._ID}), " +
                "FOREIGN KEY($COLUMN_LEADER_ID) REFERENCES ${LeaderTable.TABLE_NAME}(${LeaderTable._ID}));"

    val SQL_DROP_TABLE: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"
}