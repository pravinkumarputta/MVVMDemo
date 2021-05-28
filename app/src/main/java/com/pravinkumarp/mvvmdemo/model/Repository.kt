package com.pravinkumarp.mvvmdemo.model

import androidx.lifecycle.MutableLiveData
import com.pravinkumarp.mvvmdemo.model.bean.Fruit

interface Repository {
    fun getAllFruits(): MutableLiveData<ArrayList<Fruit>>
    fun getFruit(id: Int): MutableLiveData<Fruit?>
    fun addFruit(fruit: Fruit): Boolean
    fun updateFruit(fruit: Fruit): Boolean
    fun deleteFruit(fruit: Fruit): Boolean
}