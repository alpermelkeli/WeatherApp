package com.alpermelkeli.weatherapp.repository.location

import android.content.Context
import com.alpermelkeli.weatherapp.model.Location

object LocationStorage {
    private var dbHelper: LocationDatabaseHelper? = null

    fun initialize(context: Context) {
        if (dbHelper == null) {
            dbHelper = LocationDatabaseHelper(context)
        }
    }

    fun insertLocation(location: Location): Long {
        return dbHelper?.insertLocation(location) ?: -1
    }

    fun getLocation(): Location? {
        return dbHelper?.getLocation()
    }
}
