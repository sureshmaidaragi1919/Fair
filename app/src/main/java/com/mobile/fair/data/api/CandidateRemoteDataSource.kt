package com.mobile.fair.data.api

import javax.inject.Inject

class CandidateRemoteDataSource @Inject constructor(
    private val candidateService: CandidateService
) : BaseDataSource() {

    suspend fun getCandidateList() = getResult { candidateService.getAllCandidate() }
    suspend fun getCandidateDetails(id: String) =
        getResult { candidateService.getCandidateDetails(id) }
}