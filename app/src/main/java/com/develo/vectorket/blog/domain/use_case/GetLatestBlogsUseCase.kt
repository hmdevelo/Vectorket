package com.develo.vectorket.blog.domain.use_case

import com.develo.vectorket.blog.domain.repository.BlogRepository

class GetLatestBlogsUseCase(
    private val repository: BlogRepository
) {
    suspend operator fun invoke() = repository.getLatestBlogs()
}