package com.pixelart.week5daily1.di

import android.app.Activity
import android.databinding.DataBindingUtil
import com.pixelart.week5daily1.R
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.common.USERS_BASE_URL
import com.pixelart.week5daily1.databinding.ActivityMainBinding
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.remote.RemoteService
import com.pixelart.week5daily1.ui.mainscreen.MainActivity
import com.pixelart.week5daily1.ui.mainscreen.MainScreenContract
import com.pixelart.week5daily1.ui.mainscreen.MainScreenPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val baseActivity: BaseActivity<*>){

    @Provides
    fun providesMainscreenPresenter():MainScreenContract.Presenter = MainScreenPresenter(baseActivity as MainActivity)

    @Provides
    fun providesMainscreenBinder():ActivityMainBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_main)

    @Provides
    fun providesRemoteService(retrofit: Retrofit):RemoteService{
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(USERS_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}