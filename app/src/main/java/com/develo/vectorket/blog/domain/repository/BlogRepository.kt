package com.develo.vectorket.blog.domain.repository

import com.develo.vectorket.blog.domain.alias.BlogsResponse

interface BlogRepository {

    suspend fun getLatestBlogs(): BlogsResponse
}