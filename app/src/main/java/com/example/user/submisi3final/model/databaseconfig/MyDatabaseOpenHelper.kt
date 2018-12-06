package com.example.user.submisi3final.model.databaseconfig

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.user.submisi3final.model.table.Favorite
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
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.EVENT_DATE to TEXT,
            Favorite.EVENT_TEAMA to TEXT,
            Favorite.EVENT_TEAMH to TEXT,
            Favorite.EVENT_IDTEAMA to TEXT,
            Favorite.EVENT_IDTEAMH to TEXT,
            Favorite.EVENT_SCOREA to TEXT,
            Favorite.EVENT_SCOREH to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)