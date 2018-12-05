package com.pixelart.week5daily1.ui.mainscreen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.pixelart.week5daily1.adapter.RecyclerViewAdapter
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.databinding.ActivityMainBinding
import com.pixelart.week5daily1.di.ApplicationModule
import com.pixelart.week5daily1.di.DaggerApplicationComponent
import com.pixelart.week5daily1.di.NetworkModule
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.model.Item
import com.pixelart.week5daily1.model.UserData
import javax.inject.Inject

class MainActivity : BaseActivity<MainScreenContract.Presenter>(),
    MainScreenContract.View, RecyclerViewAdapter.OnItemClickedListener {

    val TAG = "MainActivity"

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerViewAdapter

    @Inject
    lateinit var presenter: MainScreenContract.Presenter
    @Inject
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutManager = LinearLayoutManager(this)
        presenter.loadUsers("tom")
    }

    override fun getViewPresenter(): MainScreenContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectMainScreen(this)

    }

    override fun showUsers(users: GitHubUser) {
       //Initialize recyclerview adapter here
        var userList = ArrayList<UserData>()

        for (i in 0 until users.items.size) {

            userList.add(UserData(users.items[i].login, users.items[i].avatarUrl))

            Log.d(TAG, users.items[i].login)
        }
        adapter = RecyclerViewAdapter(userList, this)
        mainBinding.recyclerView.layoutManager = layoutManager
        mainBinding.recyclerView.adapter = adapter
    }


    override fun onItemClicked(position: Int) {

    }

}
