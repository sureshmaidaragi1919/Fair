package com.mobile.fair.data.repository


import com.mobile.fair.data.api.CandidateRemoteDataSource
import com.mobile.fair.data.local.CandidateDao
import com.mobile.fair.utils.performGetOperation
import javax.inject.Inject

class CandidateRepository @Inject constructor(
    private val remoteDataSource: CandidateRemoteDataSource,
    private val localDataSource: CandidateDao
) {

    fun getCandidateDetails(id: String) = performGetOperation(
        databaseQuery = { localDataSource.getCandidateDetails(id) },
        networkCall = { remoteDataSource.getCandidateDetails(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCandidateList() = performGetOperation(
        databaseQuery = { localDataSource.getCandidateList() },
        networkCall = { remoteDataSource.getCandidateList() },
        saveCallResult = { localDataSource.insertAll(it.data) }
    )
}