package com.develo.vectorket.home.domain.repository

import com.develo.vectorket.home.domain.alias.LatestVectorResponse

interface HomeRepository {
    suspend fun getLatestVector(): LatestVectorResponse
}