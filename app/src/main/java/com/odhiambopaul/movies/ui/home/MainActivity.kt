  package com.odhiambopaul.movies.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.network.PopularMovies
import com.odhiambopaul.movies.network.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

  class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val compositeDisposable = CompositeDisposable()
//        compositeDisposable.add(
//            ServiceBuilder.buildService().getMovies(api_key)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
        CoroutineScope(Dispatchers.IO).launch {
            val response=ServiceBuilder.buildService().getMovies()
            withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful)
                    {
                        onResponse(response.body()!!)
                    }
                    else{
                        Toast.makeText(this@MainActivity, "Error: ${response.code()}",Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: HttpException)
                {
                    onFailure(e)
                }
                catch (e: Throwable)
                {
                    onFailure(e)
                }
            }
        }

    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: PopularMovies) {
        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                MoviesAdapter(response.results,this@MainActivity)
        }
    }
}
