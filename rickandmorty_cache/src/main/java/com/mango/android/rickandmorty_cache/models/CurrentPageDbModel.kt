package com.mango.android.rickandmorty_cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_page_cached")
data class CurrentPageDbModel(
    @PrimaryKey
    @ColumnInfo(name = "listID")
    val listID: Int,
    @ColumnInfo(name = "currentPage")
    val currentPage: Int
)