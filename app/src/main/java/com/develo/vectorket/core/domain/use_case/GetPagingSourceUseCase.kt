package com.develo.vectorket.core.domain.use_case

import com.develo.vectorket.core.data.paging.VectorPagingSource
import com.google.firebase.firestore.Query

class GetPagingSourceUseCase {
    operator fun invoke(query: Query) = VectorPagingSource(query)
}