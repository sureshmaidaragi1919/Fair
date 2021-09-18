package com.mobile.fair.view.candidates

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mobile.fair.data.repository.CandidateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CandidateListViewModel @Inject constructor(
    private val repository: CandidateRepository
) : ViewModel() {

    val candidateList = repository.getCandidateList()
}
