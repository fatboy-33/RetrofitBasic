package com.example.retrofitbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.retrofitbasic.api.ApiInterface
import com.example.retrofitbasic.api.RetrofitClient
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserList()
    }

    private fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        txtData = findViewById(R.id.txtData)

        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful) {
//success response
//response.body()?.data?.get(0)?.email.let { Log.i("String", it.toString()) }
                    txtData.text = response.body()?.data?.get(0)?.firstName
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }

    }
}