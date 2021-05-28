package com.pravinkumarp.mvvmdemo.viewmodel

import androidx.lifecycle.*
import com.pravinkumarp.mvvmdemo.model.bean.Fruit

interface CommonViewModel:LifecycleObserver {
    fun getAllFruits(): LiveData<ArrayList<Fruit>>
    fun getFruit(id: Int): LiveData<Fruit?>
    fun getError(): LiveData<String>
    fun addFruit(fruit: Fruit)
    fun updateFruit(fruit: Fruit)
    fun deleteFruit(fruit: Fruit)
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun refreshData(owner: LifecycleOwner)
}