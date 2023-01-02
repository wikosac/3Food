package org.d3ifcool.a3food.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3ifcool.a3food.data.Food
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/food-e94a6.appspot.com/o/toko%2F"
private const val SUFFIX_URL = ".png?alt=media"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PlaceApiService {
    @GET("static-api.json")
    suspend fun getPlace(): List<Food>
}

object PlaceApi {
    val service: PlaceApiService by lazy {
        retrofit.create(PlaceApiService::class.java)
    }

    fun getPlaceUrl(nama: String): String {
        return "$BASE_URL $nama $SUFFIX_URL"
    }
}