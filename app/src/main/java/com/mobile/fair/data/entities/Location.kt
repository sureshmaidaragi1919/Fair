package com.mobile.fair.data.entities

import androidx.room.Entity

@Entity(tableName = "location")
data class Location(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String
)