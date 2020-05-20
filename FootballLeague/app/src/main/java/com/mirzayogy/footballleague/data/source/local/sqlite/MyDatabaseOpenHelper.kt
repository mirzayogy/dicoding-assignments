package com.mirzayogy.footballleague.data.source.local.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
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
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID_EVENT to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.STR_HOME_TEAM to TEXT,
            Favorite.ID_HOME_TEAM to TEXT,
            Favorite.INT_HOME_SCORE to TEXT,
            Favorite.STR_HOME_GOAL_DETAILS to TEXT,
            Favorite.STR_AWAY_TEAM to TEXT,
            Favorite.ID_AWAY_TEAM to TEXT,
            Favorite.INT_AWAY_SCORE to TEXT,
            Favorite.STR_AWAY_GOAL_DETAILS to TEXT,
            Favorite.INT_SPECTATORS to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.STR_TIME to TEXT,
            Favorite.STR_EVENT to TEXT,
            Favorite.STR_LEAGUE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)