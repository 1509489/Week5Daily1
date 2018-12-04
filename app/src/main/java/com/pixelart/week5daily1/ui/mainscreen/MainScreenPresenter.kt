package com.pixelart.week5daily1.ui.mainscreen

import android.util.Log
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.remote.RemoteHelper
import com.pixelart.week5daily1.remote.RemoteService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenPresenter(private val view: MainScreenContract.View): MainScreenContract.Presenter{

    @Inject
    lateinit var remoteService: RemoteService

    private var subscription: Disposable? = null

    override fun loadUsers(user: String) {
       /* subscription = remoteService
            .getUsers(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                users: List<GitHubUser>, t: Throwable? ->
                view.showUsers(users!!)
                t?.printStackTrace()
                Log.d("data", "${users.totalCount}")
            }*/

        RemoteHelper.getGithubusers(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<GitHubUser> {
                override fun onSuccess(t: GitHubUser) {/*
                    for (i in 0 until t.items.size)
                    {
                        Log.d("data", "Username: ${t.items[i].login}")
                    }*/
                }

                override fun onSubscribe(d: Disposable) {
                    //d.dispose()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

    }

    override fun onDestroy() {
        subscription?.dispose()
    }

}