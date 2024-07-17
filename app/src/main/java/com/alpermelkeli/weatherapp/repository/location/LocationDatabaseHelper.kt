package com.alpermelkeli.weatherapp.repository.location

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alpermelkeli.weatherapp.model.Location

class LocationDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
                db?.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
                onCreate(db)
        }

        fun insertLocation(location: Location): Long {
                val db = this.writableDatabase

                db.delete(TABLE_NAME, null, null)

                val contentValues = ContentValues().apply {
                        put(COLUMN_CITY, location.city)
                }

                return db.insert(TABLE_NAME, null, contentValues)
        }

        fun getLocation(): Location? {
                val db = this.readableDatabase
                val cursor: Cursor = db.query(TABLE_NAME, arrayOf(COLUMN_CITY), null, null, null, null, null)
                var location: Location? = null
                if (cursor.moveToFirst()) {
                        val city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CITY))
                        location = Location(city)
                }
                cursor.close()
                return location
        }

        companion object {
                private const val DATABASE_NAME = "location.db"
                private const val DATABASE_VERSION = 1
                private const val TABLE_NAME = "location"
                private const val COLUMN_CITY = "city"

                private const val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_CITY TEXT PRIMARY KEY
            )
        """
        }
}
