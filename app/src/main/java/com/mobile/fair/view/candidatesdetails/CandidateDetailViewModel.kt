package com.mobile.fair.view.candidatesdetails

import androidx.lifecycle.*
import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.repository.CandidateRepository
import com.mobile.fair.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CandidateDetailViewModel @Inject constructor(
    private val repository: CandidateRepository
) : ViewModel(), LifecycleObserver {

    private val _id = MutableLiveData<String>()

    private val _candidateDetails = _id.switchMap { id ->
        repository.getCandidateDetails(id)
    }
    val candidateDetails: LiveData<Resource<Candidate>> = _candidateDetails


    fun start(id: String) {
        _id.value = id
    }

}
