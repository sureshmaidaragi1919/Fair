package com.mobile.fair.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.repository.CandidateRepository
import com.mobile.fair.utils.Resource
import com.mobile.fair.view.candidatesdetails.CandidateDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CandidateDetailViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: CandidateRepository

    @MockK
    lateinit var viewModel: CandidateDetailViewModel

    @MockK
    lateinit var observer: Observer<Resource<Candidate>>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockKAnnotations.init(this)
        repository = mockk(relaxed = true)
        observer = mockk(relaxed = true)
        viewModel = CandidateDetailViewModel(repository)
    }

    @Test
    fun testHasNoObserver() {
        val hasObserver = viewModel.candidateDetails.hasObservers()
        Assert.assertFalse(hasObserver)
    }

    @Test
    fun testHasObserver() {
        viewModel.candidateDetails.observeForever(observer)
        val hasObserver = viewModel.candidateDetails.hasActiveObservers()
        Assert.assertTrue(hasObserver)
    }
}