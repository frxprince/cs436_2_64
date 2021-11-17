package com.example.watermeter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabase(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        Log.v("dbwater","database created")
        var SQL= """create table waterdata(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
            meter_id INTEGER, meter_value INTEGER, timestamp datetime default current_timestamp);
        """.trimMargin()

        p0?.execSQL(SQL)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    Log.v("dbwater","database upgrade")
    }
}