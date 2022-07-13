package com.suitmedia.application.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suitmedia.application.data.ApiConfig
import com.suitmedia.application.data.response.DataItem
import com.suitmedia.application.data.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<DataItem>>()
    val listUsers : LiveData<List<DataItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(1, 12)
        client.enqueue(object : Callback<UserResponse>{


            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.data as List<DataItem>?
                } else {
                    Log.e("", "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("", "onFailure: ${t.message}", )
            }
        })
    }
}