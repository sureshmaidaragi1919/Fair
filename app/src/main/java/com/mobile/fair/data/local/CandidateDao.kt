package com.mobile.fair.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.fair.data.entities.Candidate

@Dao
interface CandidateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(candidate: Candidate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(candidateList: List<Candidate>)

    @Query("SELECT * FROM candidate")
    fun getCandidateList(): LiveData<List<Candidate>>

    @Query("SELECT * FROM candidate WHERE id = :id")
    fun getCandidateDetails(id: String): LiveData<Candidate>


}