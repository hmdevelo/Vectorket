package com.develo.vectorket.reel.domain.use_case

import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.domain.repository.ReelRepository

class UpdateDownloadUseCase(
    private val repository: ReelRepository
) {
    suspend operator fun invoke(vector: Vector, increment: Long) {
        repository.updateDownload(vector, increment)
    }
}