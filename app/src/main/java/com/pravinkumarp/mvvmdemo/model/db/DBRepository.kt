package com.pravinkumarp.mvvmdemo.model.db

import com.pravinkumarp.mvvmdemo.model.bean.Fruit

interface DBRepository {
    fun addFruit(fruit: Fruit): Boolean
    fun getAllFruits(): ArrayList<Fruit>
    fun getFruit(id: Int): Fruit?
    fun updateFruit(fruit: Fruit): Boolean
    fun deleteFruit(fruit: Fruit): Boolean
}