package com.theappmakerbuddy.newshub.data.database.entity

import androidx.room.ColumnInfo

data class SavedSourceEntity(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)