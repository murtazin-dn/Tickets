package com.example.data.repository

interface CacheRepository {
    fun saveFromText(from: String)
    fun getFromText(): String
}