package com.develo.vectorket.reel.data.service

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.develo.vectorket.reel.domain.service.Downloader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloaderImpl @Inject constructor(
    @ApplicationContext context: Context
) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun download(url: String, title: String): Long {
        return try {
            Log.e("Download", title)
            val request = DownloadManager.Request(url.toUri())
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "")
                .setTitle(title)
            downloadManager.enqueue(request)
        } catch (e: Exception) {
            e.printStackTrace()
            -1L
        }
    }
}