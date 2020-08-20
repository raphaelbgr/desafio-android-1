package com.picpay.desafio.android

class ExampleService(
    private val service: com.picpay.desafio.android.repository.api.PicPayService
) {

    fun example(): List<com.picpay.desafio.android.repository.model.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}