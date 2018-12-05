package com.pixelart.week5daily1.ui.mainscreen

import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.remote.RemoteHelper
import com.pixelart.week5daily1.remote.RemoteService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenPresenter(private val view: MainScreenContract.View, private val remoteService: RemoteService):
    MainScreenContract.Presenter {


    private var subscription: Disposable? = null

    override fun onResume() {
        //subscription = remoteService.getUsers()
    }

    override fun loadUsers(user: String) {
        subscription = remoteService
            .getUsers(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (

                {users -> view.showUsers(users)},
                {view.showError("Error data loading failed")}
            )
    }

    override fun onDestroy() {
        subscription?.dispose()
    }

}