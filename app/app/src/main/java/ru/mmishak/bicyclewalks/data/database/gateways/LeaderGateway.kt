package ru.mmishak.bicyclewalks.data.database.gateways

import android.content.ContentValues
import ru.mmishak.bicyclewalks.App
import ru.mmishak.bicyclewalks.data.database.DbHelper
import ru.mmishak.bicyclewalks.data.database.tables.LeaderTable
import ru.mmishak.bicyclewalks.data.entities.HumanEntity

object LeaderGateway : Gateway<HumanEntity> {
    override fun read(id: Int): HumanEntity? {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                LeaderTable._ID,
                LeaderTable.COLUMN_LOGIN,
                LeaderTable.COLUMN_PASSWORD,
                LeaderTable.COLUMN_EMAIL,
                LeaderTable.COLUMN_FIRST_NAME,
                LeaderTable.COLUMN_SECOND_NAME,
                LeaderTable.COLUMN_PHONE)
        val selection = "${LeaderTable._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(
                LeaderTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )
        with(cursor) {
            return when {
                moveToNext() -> HumanEntity(
                        id = getInt(getColumnIndexOrThrow(LeaderTable._ID)),
                        login = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_LOGIN)),
                        password = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_PASSWORD)),
                        email = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_EMAIL)),
                        firstName = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_FIRST_NAME)),
                        secondName = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_SECOND_NAME)),
                        phone = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_PHONE))
                )
                else -> null
            }
        }
    }

    override fun update(entity: HumanEntity) {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(LeaderTable.COLUMN_LOGIN, entity.login)
            put(LeaderTable.COLUMN_PASSWORD, entity.password)
            put(LeaderTable.COLUMN_EMAIL, entity.email)
            put(LeaderTable.COLUMN_FIRST_NAME, entity.firstName)
            put(LeaderTable.COLUMN_SECOND_NAME, entity.secondName)
            put(LeaderTable.COLUMN_PHONE, entity.phone)
        }
        val selection = "${LeaderTable._ID} LIKE ?"
        val selectionArgs = arrayOf(entity.id.toString())
        db.update(LeaderTable.TABLE_NAME, values, selection, selectionArgs)
    }

    override fun getAll(): List<HumanEntity> {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                LeaderTable._ID,
                LeaderTable.COLUMN_LOGIN,
                LeaderTable.COLUMN_PASSWORD,
                LeaderTable.COLUMN_EMAIL,
                LeaderTable.COLUMN_FIRST_NAME,
                LeaderTable.COLUMN_SECOND_NAME,
                LeaderTable.COLUMN_PHONE)
        val cursor = db.query(
                LeaderTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        val results = mutableListOf<HumanEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(HumanEntity(
                        id = getInt(getColumnIndexOrThrow(LeaderTable._ID)),
                        login = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_LOGIN)),
                        password = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_PASSWORD)),
                        email = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_EMAIL)),
                        firstName = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_FIRST_NAME)),
                        secondName = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_SECOND_NAME)),
                        phone = getString(getColumnIndexOrThrow(LeaderTable.COLUMN_PHONE))
                ))
            }
        }
        return results
    }

    override fun delete(id: Int) {
        val db = DbHelper(App.getContext()).writableDatabase
        val selection = "${LeaderTable._ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        db.delete(LeaderTable.TABLE_NAME, selection, selectionArgs)
    }

    override fun create(entity: HumanEntity) {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(LeaderTable.COLUMN_LOGIN, entity.login)
            put(LeaderTable.COLUMN_PASSWORD, entity.password)
            put(LeaderTable.COLUMN_EMAIL, entity.email)
            put(LeaderTable.COLUMN_FIRST_NAME, entity.firstName)
            put(LeaderTable.COLUMN_SECOND_NAME, entity.secondName)
            put(LeaderTable.COLUMN_PHONE, entity.phone)
        }
        db.insert(LeaderTable.TABLE_NAME, null, values)
    }
}