package com.mobile.fair.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.fair.data.entities.Candidate

@Database(entities = [Candidate::class], version = 1, exportSchema = false)
abstract class CandidateDatabase : RoomDatabase() {

    abstract fun candidateDao(): CandidateDao

    companion object {
        @Volatile
        private var instance: CandidateDatabase? = null

        fun getDatabase(context: Context): CandidateDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, CandidateDatabase::class.java, "fair")
                .fallbackToDestructiveMigration()
                .build()
    }

}