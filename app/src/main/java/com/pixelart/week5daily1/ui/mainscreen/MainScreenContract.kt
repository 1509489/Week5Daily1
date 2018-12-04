package com.pixelart.week5daily1.ui.mainscreen

import com.pixelart.week5daily1.base.BasePresenter
import com.pixelart.week5daily1.base.BaseView
import com.pixelart.week5daily1.model.Item

interface MainScreenContract{
    interface View: BaseView{
        fun showUsers(users: List<Item>)
    }

    interface Presenter: BasePresenter{
        fun loadUsers(users: String)
    }
}