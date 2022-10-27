package com.example.retrofitbasic

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitbasic.api.ApiInterface
import com.example.retrofitbasic.api.RetrofitClient
import java.lang.Exception

class MainActivity : AppCompatActivity() {
//    lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getUserList()

        val loading = findViewById<ProgressBar>(R.id.progress_bar)
        lifecycleScope.launchWhenCreated {
            loading.visibility = View.VISIBLE
            val response = try{
                RetrofitClient.api.getAllUsers()
            }catch (e: Exception){
                Log.e(TAG, "Exception: $e")
                loading.visibility = View.GONE
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                Toast.makeText(this@MainActivity, ""+response.body()?.perPage+" users loaded!", Toast.LENGTH_SHORT).show()
                val usersRecyclerView = findViewById<RecyclerView>(R.id.users_list_view).apply {
                    adapter = UserAdapter(){it}
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    setHasFixedSize(true)
                }
                (usersRecyclerView.adapter as UserAdapter).submitList(response.body()!!.data)
                loading.visibility = View.GONE
            } else {
                Toast.makeText(this@MainActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
                loading.visibility = View.GONE
            }
        }
    }
    }

//    private fun getUserList() {
//        var retrofit = RetrofitClient.getInstance()
//        var apiInterface = retrofit.create(ApiInterface::class.java)
//        txtData = findViewById(R.id.txtData)
//
//        lifecycleScope.launchWhenCreated {
//            try {
//                val response = apiInterface.getAllUsers()
//                if (response.isSuccessful) {
////success response
////response.body()?.data?.get(0)?.email.let { Log.i("String", it.toString()) }
//                    txtData.text = response.body()?.data?.get(0)?.firstName
//                } else {
//                    Toast.makeText(
//                        this@MainActivity,
//                        response.errorBody().toString(),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }catch (Ex:Exception){
//                Log.e("Error",Ex.localizedMessage)
//            }
//        }
//
//    }


