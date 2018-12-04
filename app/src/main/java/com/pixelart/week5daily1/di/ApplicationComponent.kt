package com.pixelart.week5daily1.di

import com.pixelart.week5daily1.ui.mainscreen.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun injectMainScreen(mainScreen: MainActivity)
}