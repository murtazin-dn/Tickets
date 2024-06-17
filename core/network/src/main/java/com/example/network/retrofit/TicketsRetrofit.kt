package com.example.network.retrofit

import com.example.common.network.ApiResponse
import com.example.common.network.safeApiCall
import com.example.network.TicketsNetworkDataSource
import com.example.network.di.NetworkScope
import com.example.network.dto.offers.OffersDto
import com.example.network.dto.tickets.TicketsDto
import com.example.network.dto.ticketsoffers.TicketsOffersDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

@NetworkScope
internal class TicketsRetrofit @Inject constructor(
    json: Json,
    okHttpClient: OkHttpClient
) : TicketsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(FOODIES_BASE_URL)
        .client(okHttpClient)
        .build()
        .create(RetrofitTicketsNetworkApi::class.java)
    override suspend fun getOffers(): ApiResponse<OffersDto> =
        safeApiCall { networkApi.getOffers() }

    override suspend fun getTickets(): ApiResponse<TicketsDto> =
        safeApiCall { networkApi.getTickets() }

    override suspend fun getTicketsOffers(): ApiResponse<TicketsOffersDto> =
        safeApiCall { networkApi.getTicketsOffers() }
}

private interface RetrofitTicketsNetworkApi{

    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): Response<OffersDto>

    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun getTickets(): Response<TicketsDto>

    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun getTicketsOffers(): Response<TicketsOffersDto>
}

private const val FOODIES_BASE_URL = "https://run.mocky.io/v3/"
