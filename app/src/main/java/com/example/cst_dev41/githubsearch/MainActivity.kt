package com.example.cst_dev41.githubsearch

import android.app.ProgressDialog
import android.content.ClipData
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cst_dev41.githubsearch.adapter.activityMainAdapter
import com.example.cst_dev41.githubsearch.api.Client
import com.example.cst_dev41.githubsearch.api.Service
import com.example.cst_dev41.githubsearch.model.GithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.widget.SearchView
import android.support.v4.view.MenuItemCompat.getActionView
import com.example.cst_dev41.githubsearch.adapter.EndlessOnScrollListener






class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)//Initialize layoutmanager
        recyclerView!!.smoothScrollToPosition(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.menuSearch)

        val searchView = searchItem.getActionView() as android.widget.SearchView
        searchView.setQueryHint("Search View Hint")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                initViews()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                try {
                    val Client = Client
                    val apiService = Client.client?.create(Service::class.java)
                    val username = query

                    if (username == "") {
                        Toast.makeText(this@MainActivity, "username feild not filled", Toast.LENGTH_LONG).show()
                    } else {
                        val s = "$username"
                        val call = apiService?.getItems(s)
                        //Toast.makeText(this@MainActivity, call?.request()?.url().toString(), Toast.LENGTH_LONG).show()
                        call?.enqueue(object : Callback<GithubResponse> {
                            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                                val items = response.body().items

                                recyclerView!!.adapter = activityMainAdapter(applicationContext, items!!)
                                recyclerView!!.smoothScrollToPosition(5)
                            }

                            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                                Log.d("Error", t.message)
                                Toast.makeText(this@MainActivity, "Please check your internet!", Toast.LENGTH_LONG).show()

                            }
                        })
                    }
                } catch (e: Exception) {
                    Log.d("Error", e.message)
                }

                return false
            }

        })

        return true
    }

}
