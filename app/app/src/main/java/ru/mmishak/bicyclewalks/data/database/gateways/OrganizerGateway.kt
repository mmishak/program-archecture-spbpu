package ru.mmishak.bicyclewalks.data.database.gateways

import android.content.ContentValues
import ru.mmishak.bicyclewalks.App
import ru.mmishak.bicyclewalks.data.database.DbHelper
import ru.mmishak.bicyclewalks.data.database.tables.OrganizerTable
import ru.mmishak.bicyclewalks.data.entities.OrganizerEntity

object OrganizerGateway : Gateway<OrganizerEntity> {
    override fun read(id: Int): OrganizerEntity? {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                OrganizerTable._ID,
                OrganizerTable.COLUMN_LOGIN,
                OrganizerTable.COLUMN_PASSWORD,
                OrganizerTable.COLUMN_TITLE)
        val selection = "${OrganizerTable._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(
                OrganizerTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )
        with(cursor) {
            return when {
                moveToNext() -> OrganizerEntity(
                        id = getInt(getColumnIndexOrThrow(OrganizerTable._ID)),
                        login = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_LOGIN)),
                        password = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_PASSWORD)),
                        email = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_EMAIL)),
                        title = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_TITLE))
                )
                else -> null
            }
        }
    }

    override fun update(entity: OrganizerEntity) {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(OrganizerTable.COLUMN_LOGIN, entity.login)
            put(OrganizerTable.COLUMN_PASSWORD, entity.password)
            put(OrganizerTable.COLUMN_EMAIL, entity.email)
            put(OrganizerTable.COLUMN_TITLE, entity.title)
        }
        val selection = "${OrganizerTable._ID} LIKE ?"
        val selectionArgs = arrayOf(entity.id.toString())
        db.update(OrganizerTable.TABLE_NAME, values, selection, selectionArgs)
    }

    override fun getAll(): List<OrganizerEntity> {
        val db = DbHelper(App.getContext()).readableDatabase
        val projection = arrayOf(
                OrganizerTable._ID,
                OrganizerTable.COLUMN_LOGIN,
                OrganizerTable.COLUMN_PASSWORD,
                OrganizerTable.COLUMN_EMAIL,
                OrganizerTable.COLUMN_TITLE)
        val cursor = db.query(
                OrganizerTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        val results = mutableListOf<OrganizerEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(OrganizerEntity(
                        id = getInt(getColumnIndexOrThrow(OrganizerTable._ID)),
                        login = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_LOGIN)),
                        password = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_PASSWORD)),
                        email = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_EMAIL)),
                        title = getString(getColumnIndexOrThrow(OrganizerTable.COLUMN_TITLE))
                ))
            }
        }
        return results
    }

    override fun delete(id: Int) {
        val db = DbHelper(App.getContext()).writableDatabase
        val selection = "${OrganizerTable._ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        db.delete(OrganizerTable.TABLE_NAME, selection, selectionArgs)
    }

    override fun create(entity: OrganizerEntity): Int {
        val db = DbHelper(App.getContext()).writableDatabase
        val values = ContentValues().apply {
            put(OrganizerTable.COLUMN_LOGIN, entity.login)
            put(OrganizerTable.COLUMN_PASSWORD, entity.password)
            put(OrganizerTable.COLUMN_EMAIL, entity.email)
            put(OrganizerTable.COLUMN_TITLE, entity.title)
        }
        return db.insert(OrganizerTable.TABLE_NAME, null, values).toInt()
    }
}