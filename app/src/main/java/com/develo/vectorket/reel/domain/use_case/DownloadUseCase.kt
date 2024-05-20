package com.develo.vectorket.reel.domain.use_case

import com.develo.vectorket.reel.domain.service.Downloader

class DownloadUseCase(
    private val downloader: Downloader
) {
    operator fun invoke(url: String, title: String) = downloader.download(url, title)
}