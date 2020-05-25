package com.mirzayogy.footballleague.data.source.local.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(EventResponse.TABLE_FAVORITE, true,
            EventResponse.ID_EVENT to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            EventResponse.STR_HOME_TEAM to TEXT,
            EventResponse.ID_HOME_TEAM to TEXT,
            EventResponse.INT_HOME_SCORE to TEXT,
            EventResponse.STR_HOME_GOAL_DETAILS to TEXT,
            EventResponse.STR_AWAY_TEAM to TEXT,
            EventResponse.ID_AWAY_TEAM to TEXT,
            EventResponse.INT_AWAY_SCORE to TEXT,
            EventResponse.STR_AWAY_GOAL_DETAILS to TEXT,
            EventResponse.INT_SPECTATORS to TEXT,
            EventResponse.DATE_EVENT to TEXT,
            EventResponse.STR_TIME to TEXT,
            EventResponse.STR_EVENT to TEXT,
            EventResponse.STR_LEAGUE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(EventResponse.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)