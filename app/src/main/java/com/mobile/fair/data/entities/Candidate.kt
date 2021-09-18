package com.mobile.fair.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "candidate")
data class Candidate(
    @PrimaryKey
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String?,
    val email: String?,
    val dateOfBirth: String?,
    val phone: String?,
    @Embedded
    val location: Location?
)