package ru.mmishak.bicyclewalks.data.database.gateways

import android.content.ContentValues
import ru.mmishak.bicyclewalks.App
import ru.mmishak.bicyclewalks.data.database.DbHelper
import ru.mmishak.bicyclewalks.data.database.tables.BicycleWalkTable
import ru.mmishak.bicyclewalks.data.entities.BicycleWalkEntity

object BicycleWalkGateway : Gateway<BicycleWalkEntity> {
    override fun read(id: Int): BicycleWalkEntity? {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                BicycleWalkTable._ID,
                BicycleWalkTable.COLUMN_LEADER_ID,
                BicycleWalkTable.COLUMN_ORGANIZER_ID,
                BicycleWalkTable.COLUMN_LEADER_STATUS,
                BicycleWalkTable.COLUMN_PAYMENT_TYPE,
                BicycleWalkTable.COLUMN_PRICE,
                BicycleWalkTable.COLUMN_DATE,
                BicycleWalkTable.COLUMN_DISTANCE,
                BicycleWalkTable.COLUMN_DURATION,
                BicycleWalkTable.COLUMN_WALK_TYPE,
                BicycleWalkTable.COLUMN_TITLE,
                BicycleWalkTable.COLUMN_DESCRIPTION)
        val selection = "${BicycleWalkTable._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(
                BicycleWalkTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )
        with(cursor) {
            return when {
                moveToNext() -> BicycleWalkEntity(
                        id = getInt(getColumnIndexOrThrow(BicycleWalkTable._ID)),
                        title = getString(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_TITLE)),
                        description = getString(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DESCRIPTION)),
                        date = getLong(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DATE)),
                        paymentType = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_PAYMENT_TYPE)),
                        price = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_PRICE)),
                        leader_id = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_LEADER_ID)),
                        organizer_id = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_ORGANIZER_ID)),
                        leaderStatus = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_LEADER_STATUS)),
                        distance = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DISTANCE)),
                        duration = getLong(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DURATION)),
                        walkType = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_WALK_TYPE))
                )
                else -> null
            }
        }
    }

    override fun update(entity: BicycleWalkEntity) {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(BicycleWalkTable.COLUMN_LEADER_ID, entity.leader_id)
            put(BicycleWalkTable.COLUMN_ORGANIZER_ID, entity.organizer_id)
            put(BicycleWalkTable.COLUMN_LEADER_STATUS, entity.leaderStatus)
            put(BicycleWalkTable.COLUMN_PAYMENT_TYPE, entity.paymentType)
            put(BicycleWalkTable.COLUMN_PRICE, entity.price)
            put(BicycleWalkTable.COLUMN_DATE, entity.date)
            put(BicycleWalkTable.COLUMN_DISTANCE, entity.distance)
            put(BicycleWalkTable.COLUMN_DURATION, entity.duration)
            put(BicycleWalkTable.COLUMN_WALK_TYPE, entity.walkType)
            put(BicycleWalkTable.COLUMN_TITLE, entity.title)
            put(BicycleWalkTable.COLUMN_DESCRIPTION, entity.description)
        }
        val selection = "${BicycleWalkTable._ID} LIKE ?"
        val selectionArgs = arrayOf(entity.id.toString())
        db.update(BicycleWalkTable.TABLE_NAME, values, selection, selectionArgs)
    }


    override fun getAll(): List<BicycleWalkEntity> {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                BicycleWalkTable._ID,
                BicycleWalkTable.COLUMN_LEADER_ID,
                BicycleWalkTable.COLUMN_ORGANIZER_ID,
                BicycleWalkTable.COLUMN_LEADER_STATUS,
                BicycleWalkTable.COLUMN_PAYMENT_TYPE,
                BicycleWalkTable.COLUMN_PRICE,
                BicycleWalkTable.COLUMN_DATE,
                BicycleWalkTable.COLUMN_DISTANCE,
                BicycleWalkTable.COLUMN_DURATION,
                BicycleWalkTable.COLUMN_WALK_TYPE,
                BicycleWalkTable.COLUMN_TITLE,
                BicycleWalkTable.COLUMN_DESCRIPTION)
        val cursor = db.query(
                BicycleWalkTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        val results = mutableListOf<BicycleWalkEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(BicycleWalkEntity(
                        id = getInt(getColumnIndexOrThrow(BicycleWalkTable._ID)),
                        title = getString(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_TITLE)),
                        description = getString(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DESCRIPTION)),
                        date = getLong(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DATE)),
                        paymentType = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_PAYMENT_TYPE)),
                        price = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_PRICE)),
                        leader_id = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_LEADER_ID)),
                        organizer_id = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_ORGANIZER_ID)),
                        leaderStatus = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_LEADER_STATUS)),
                        distance = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DISTANCE)),
                        duration = getLong(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_DURATION)),
                        walkType = getInt(getColumnIndexOrThrow(BicycleWalkTable.COLUMN_WALK_TYPE))
                ))
            }
        }
        return results
    }

    override fun delete(id: Int) {
        val db = DbHelper(App.getContext()).writableDatabase
        val selection = "${BicycleWalkTable._ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        db.delete(BicycleWalkTable.TABLE_NAME, selection, selectionArgs)
    }

    override fun create(entity: BicycleWalkEntity): Int {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(BicycleWalkTable.COLUMN_LEADER_ID, entity.leader_id)
            put(BicycleWalkTable.COLUMN_ORGANIZER_ID, entity.organizer_id)
            put(BicycleWalkTable.COLUMN_LEADER_STATUS, entity.leaderStatus)
            put(BicycleWalkTable.COLUMN_PAYMENT_TYPE, entity.paymentType)
            put(BicycleWalkTable.COLUMN_PRICE, entity.price)
            put(BicycleWalkTable.COLUMN_DATE, entity.date)
            put(BicycleWalkTable.COLUMN_DISTANCE, entity.distance)
            put(BicycleWalkTable.COLUMN_DURATION, entity.duration)
            put(BicycleWalkTable.COLUMN_WALK_TYPE, entity.walkType)
            put(BicycleWalkTable.COLUMN_TITLE, entity.title)
            put(BicycleWalkTable.COLUMN_DESCRIPTION, entity.description)
        }
        return db.insert(BicycleWalkTable.TABLE_NAME, null, values).toInt()
    }
}