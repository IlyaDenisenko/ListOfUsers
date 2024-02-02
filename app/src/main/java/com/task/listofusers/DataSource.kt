package com.task.listofusers

class DataSource {

    private val mService: RetrofitApi = RetrofitClient.retrofitApi
    private lateinit var usersModel: UsersModel

    suspend fun getData(): UsersModel{
        val data = mService.getUserList().body()
        usersModel = UsersModel(data!!.info, data.results)
        return usersModel
    }

    suspend fun getDataBySeed(seed: String, results: Long): UsersModel{
        val data = mService.getUsersBySeed(seed, results).body()
        usersModel = UsersModel(data!!.info, data.results)
        return usersModel
    }
}
