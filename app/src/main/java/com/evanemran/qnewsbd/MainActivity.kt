package com.evanemran.qnewsbd

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.qnewsbd.adapters.HeadlinesAdapter
import com.evanemran.qnewsbd.listeners.ResponseListener
import com.evanemran.qnewsbd.manager.RequestManager
import com.evanemran.qnewsbd.models.NewsApiResponse

class MainActivity : AppCompatActivity() {
    var dialog: ProgressDialog? = null
    var recyclerHeadline: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = ProgressDialog(this)
        dialog!!.setTitle("Loading...")

        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener)
        dialog!!.show()
    }

    private val listener: ResponseListener<NewsApiResponse> = object : ResponseListener<NewsApiResponse> {
        override fun didFetch(response: NewsApiResponse, message: String) {
            dialog!!.dismiss()
            recyclerHeadline = findViewById(R.id.recycler_headlines)
            recyclerHeadline!!.setHasFixedSize(true)

            recyclerHeadline!!.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val adapter = response.channel!!.itemList?.let { HeadlinesAdapter(this@MainActivity, it) }
            recyclerHeadline!!.adapter = adapter
        }

        override fun didError(message: String) {
            dialog!!.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

    }
}