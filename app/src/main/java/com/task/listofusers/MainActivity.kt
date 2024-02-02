package com.task.listofusers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.listofusers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeLayout: SwipeRefreshLayout
    private  var adapter: UserAdapter = UserAdapter(this, this)
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var users: UsersModel
    private val PREFS_FILE = "users_prefs"
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        swipeLayout = binding.swipeRefresh
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        if (sharedPreferences.contains("seed"))
            getDataBySeed()
        else
            getData()
        refreshingUserList()
        setContentView(binding.root)
    }

    override fun onClickUserPosition(position: Int) {
        val intent = Intent(this@MainActivity, UserActivity::class.java)
        val result = users.results[position]
        intent.putExtra("user", result)
        startActivity(intent)
    }

    private fun updateRecyclerView(userModel: UsersModel){
        users = UsersModel(userModel.info, userModel.results)
        val userList = userModel.results
        adapter.updateUsers(userList)
        adapter.notifyDataSetChanged()
    }

    private fun getData(){
        viewModel.getData().observe(this){response ->
           updateRecyclerView(response)
            Log.i("TAG", response.toString())
            sharedPreferences.edit().putString("seed", users.info.seed).apply()
            sharedPreferences.edit().putLong("results", users.info.results).apply()
        }
    }

    private fun getDataBySeed(){
        val seed = sharedPreferences.getString("seed", "null")!!
        val results = sharedPreferences.getLong("results", 0)
        viewModel.getDataBySeed(seed, results).observe(this){response ->
           updateRecyclerView(response)
            Log.i("TAG", response.toString())
        }
    }

    private fun refreshingUserList(){
        swipeLayout.setOnRefreshListener {
            getData()
            swipeLayout.isRefreshing = false
        }
    }
}