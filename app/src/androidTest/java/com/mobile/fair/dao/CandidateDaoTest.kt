package com.mobile.fair.dao

import androidx.lifecycle.LiveData
import com.mobile.fair.CandidateDataFactory
import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.local.CandidateDao
import com.mobile.fair.data.local.CandidateDatabase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class CandidateDaoTest {

    @MockK
    private lateinit var database: CandidateDatabase

    @MockK
    private lateinit var candidateDao: CandidateDao

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockKAnnotations.init(this)
        database = mockk(relaxed = true)
        candidateDao = database.candidateDao()
    }

    @Test
    fun checkCandidateDetailsEmpty() {
        val candidateDetails: LiveData<Candidate> = candidateDao.getCandidateDetails("1")
        Assert.assertNotEquals(0, candidateDetails)
    }

    @Test
    fun checkCandidateListEmpty() {
        val candidateList: LiveData<List<Candidate>> = candidateDao.getCandidateList()
        Assert.assertNotEquals(0, candidateList)
    }

    @Test
    fun insertCandidateDetails() = runBlocking {
        val isInserted = candidateDao.insert(CandidateDataFactory.candidate)
        Assert.assertNotNull(isInserted)
    }

    @Test
    fun insertAllCandidateDetails() = runBlocking {
        val isInserted = candidateDao.insertAll(CandidateDataFactory.candidateList)
        Assert.assertNotNull(isInserted)
    }


}