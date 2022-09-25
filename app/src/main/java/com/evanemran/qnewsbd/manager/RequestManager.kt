package com.evanemran.qnewsbd.manager

import android.content.Context
import com.evanemran.qnewsbd.listeners.ResponseListener
import com.evanemran.qnewsbd.models.NewsApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

class RequestManager(var context: Context) {

    var retrofit =  Retrofit.Builder()
        .baseUrl("https://www.jagonews24.com/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()


    fun getNewsHeadlines(listener: ResponseListener<NewsApiResponse>) {
        val call = retrofit.create(CallNewsApi::class.java).callHeadlines()
        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(it, response.message()) }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    interface CallNewsApi {
        @GET("rss/rss.xml")
        fun callHeadlines():Call<NewsApiResponse>
    }
}