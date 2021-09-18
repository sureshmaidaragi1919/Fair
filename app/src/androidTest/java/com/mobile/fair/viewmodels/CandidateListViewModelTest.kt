package com.mobile.fair.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.repository.CandidateRepository
import com.mobile.fair.utils.Resource
import com.mobile.fair.view.candidates.CandidateListViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CandidateListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: CandidateRepository

    @MockK
    lateinit var viewModel: CandidateListViewModel

    @MockK
    lateinit var observer: Observer<Resource<List<Candidate>>>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockKAnnotations.init(this)
        repository = mockk(relaxed = true)
        observer = mockk(relaxed = true)
        viewModel = CandidateListViewModel(repository)
    }

    @Test
    fun testHasNoObserver() {
        val hasObserver = viewModel.candidateList.hasObservers()
        Assert.assertFalse(hasObserver)
    }

    @Test
    fun testHasObserver() {
        viewModel.candidateList.observeForever(observer)
        val hasObserver = viewModel.candidateList.hasActiveObservers()
        Assert.assertFalse(hasObserver)
    }

}