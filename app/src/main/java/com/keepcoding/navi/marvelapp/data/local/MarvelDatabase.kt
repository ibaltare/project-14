package com.keepcoding.navi.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun getDao(): CharacterDAO
}