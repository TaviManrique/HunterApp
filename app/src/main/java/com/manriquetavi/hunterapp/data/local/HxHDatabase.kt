package com.manriquetavi.hunterapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manriquetavi.hunterapp.data.local.dao.HunterDao
import com.manriquetavi.hunterapp.data.local.dao.HunterRemoteKeysDao
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.model.HunterRemoteKeys

@Database(entities = [Hunter::class, HunterRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HxHDatabase: RoomDatabase() {

    companion object {
        fun create(context: Context, useMemory: Boolean): HxHDatabase {
            val dataBuilder = if(useMemory) {
                Room.inMemoryDatabaseBuilder(context, HxHDatabase::class.java)
            } else {
                Room.databaseBuilder(context, HxHDatabase::class.java, "test_database.db")
            }
            return dataBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun hunterDao(): HunterDao
    abstract fun hunterRemoteKeysDao(): HunterRemoteKeysDao

}