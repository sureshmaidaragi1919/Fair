package com.mobile.fair.repository

import com.mobile.fair.data.api.CandidateRemoteDataSource
import com.mobile.fair.data.local.CandidateDao
import com.mobile.fair.data.repository.CandidateRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class CandidateRepositoryTest {

    @MockK
    lateinit var candidateRepository: CandidateRepository

    @MockK
    lateinit var dataSource: CandidateRemoteDataSource


    @MockK
    private lateinit var candidateDao: CandidateDao

    @Before
    @Throws(Exception::class)
    fun setup() {

        MockKAnnotations.init(this)
        candidateDao = mockk(relaxed = true)
        dataSource = mockk(relaxed = true)
        candidateRepository = CandidateRepository(dataSource, candidateDao)
    }

    @Test
    fun checkCandidateDetailsHasData() {
        val dataExist = candidateRepository.getCandidateDetails("1")
        Assert.assertNotNull(dataExist)
    }

    @Test
    fun checkCandidateListHasData() {
        val dataExist = candidateRepository.getCandidateList()
        Assert.assertNotNull(dataExist)
    }


}