package com.develo.vectorket.auth.domain.use_case

import com.develo.vectorket.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope

class GetUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(scope: CoroutineScope) = repository.getUser(scope)
}