package com.mobile.fair.data.entities

data class CandidateList(
    val limit: Int,
    val page: Int,
    val total: Int,
    val data: List<Candidate>
)