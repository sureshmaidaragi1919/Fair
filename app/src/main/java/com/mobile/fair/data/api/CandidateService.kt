package com.mobile.fair.data.api

import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.entities.CandidateList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CandidateService {
    @GET("user?limit=50")
    suspend fun getAllCandidate(): Response<CandidateList>

    @GET("user/{id}")
    suspend fun getCandidateDetails(@Path("id") id: String): Response<Candidate>
}