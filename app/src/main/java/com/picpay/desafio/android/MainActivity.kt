package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.repository.model.User

class MainActivity : AppCompatActivity(R.layout.activity_main), com.picpay.desafio.android.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainPresenter(this).getUsers()
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

    }

    override fun onFailure() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onSuccess(list: List<User>) {
        progressBar.visibility = View.GONE

        adapter.users = list
    }
}
