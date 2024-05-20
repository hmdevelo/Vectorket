package com.develo.vectorket.blog.data.repository

import com.develo.vectorket.blog.domain.alias.BlogsResponse
import com.develo.vectorket.blog.domain.model.Blog
import com.develo.vectorket.blog.domain.repository.BlogRepository
import com.develo.vectorket.core.data.util.BLOGS_COLLECTION
import com.develo.vectorket.core.data.util.FIELD_CREATED_AT
import com.develo.vectorket.core.domain.model.Response.Failure
import com.develo.vectorket.core.domain.model.Response.Success
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class BlogRepositoryImpl @Inject constructor(
    @Named(BLOGS_COLLECTION)
    private val blogs: CollectionReference
) : BlogRepository {

    override suspend fun getLatestBlogs(): BlogsResponse {
        return try {
            val result = blogs.orderBy(FIELD_CREATED_AT, Direction.DESCENDING)
                .limit(5)
                .get()
                .await()
                .toObjects(Blog::class.java)
            Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }
}