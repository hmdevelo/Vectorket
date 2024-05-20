package com.develo.vectorket.reel.domain.service

interface Downloader {
    fun download(url: String, title: String): Long
}