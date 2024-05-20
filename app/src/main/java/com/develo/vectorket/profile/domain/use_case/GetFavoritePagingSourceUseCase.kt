package com.develo.vectorket.profile.domain.use_case

import com.develo.vectorket.core.data.query.QueryBuilder
import com.develo.vectorket.profile.data.paging.FavoritePagingSource
import com.develo.vectorket.reel.domain.model.Favorite

class GetFavoritePagingSourceUseCase(
    private val queryBuilder: QueryBuilder
) {
    operator fun invoke(favorites: List<Favorite>) = FavoritePagingSource(favorites, queryBuilder)
}