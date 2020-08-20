package com.picpay.desafio.android

import com.picpay.desafio.android.repository.UserRepositoryImpl
import com.picpay.desafio.android.repository.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: View?) : Presenter {
    private val userRepository = UserRepositoryImpl()
    override fun getUsers() {
        userRepository.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    view?.onFailure()
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    view?.onSuccess(response.body()!!)
                }
            })
    }
}

interface View {
    fun onFailure()
    fun onSuccess(list: List<User>)
}

interface Presenter {
    fun getUsers()
}