package com.pixelart.week5daily1.ui.mainscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pixelart.week5daily1.R
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.di.DaggerApplicationComponent
import com.pixelart.week5daily1.di.NetworkModule
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.model.Item
import com.pixelart.week5daily1.remote.RemoteHelper
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity<MainScreenContract.Presenter>(), MainScreenContract.View {

    val TAG = "MainActivity"

    @Inject
    lateinit var presenter: MainScreenContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getUsersResponse("me")


    }

    override fun getViewPresenter(): MainScreenContract.Presenter = presenter

    override fun init() {
        /*DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(this))
            .build().injectMainScreen(this)*/

        presenter.loadUsers("1509489")
    }

    override fun showUsers(users: List<Item>) {
        for (i in 0 until users.size)
        {
            Log.d(TAG, "Username: ${users[i].login}")
        }
        Log.d(TAG, "TotalCount: ${users.size}")
    }
/*
    fun getUsersResponse(user: String){
        RemoteHelper.getGithubusers(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<GitHubUser>{
                override fun onSuccess(t: GitHubUser) {
                    for (i in 0 until t.items.size)
                    {
                        Log.d(TAG, "Username: ${t.items[i].login}")
                    }
                    Log.d(TAG, "TotalCount: ${t.totalCount}")
                }

                override fun onSubscribe(d: Disposable) {
                    //d.dispose()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }*/
}
