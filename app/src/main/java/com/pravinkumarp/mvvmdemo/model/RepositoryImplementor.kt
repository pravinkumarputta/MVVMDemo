package com.pravinkumarp.mvvmdemo.model

import androidx.lifecycle.MutableLiveData
import com.pravinkumarp.mvvmdemo.model.bean.Fruit
import com.pravinkumarp.mvvmdemo.model.db.DBRepositoryImplementor

class RepositoryImplementor(private val dbHelperModelImplementor: DBRepositoryImplementor): Repository {
    private var fruitList = ArrayList<Fruit>()
    private val mutableFruitList = MutableLiveData<ArrayList<Fruit>>()
    private val mutableFruit = MutableLiveData<Fruit?>()

    override fun getAllFruits(): MutableLiveData<ArrayList<Fruit>> {
        fruitList = dbHelperModelImplementor.getAllFruits()
        mutableFruitList.value = fruitList
        return mutableFruitList
    }

    override fun getFruit(id: Int): MutableLiveData<Fruit?> {
        mutableFruit.value = dbHelperModelImplementor.getFruit(id)
        return mutableFruit
    }

    override fun addFruit(fruit: Fruit): Boolean {
        if (fruit.name.isEmpty()) {
            throw Exception("Please enter fruit name.")
        }
        if (fruit.description.isEmpty()) {
            throw Exception("Please enter fruit description.")
        }
        val success = dbHelperModelImplementor.addFruit(fruit)
        if (!success) {
            throw Exception("Failed to add fruit.")
        }
        getAllFruits()
        return success
    }

    override fun updateFruit(fruit: Fruit): Boolean {
        val success = dbHelperModelImplementor.updateFruit(fruit)
        if (!success) {
            throw Exception("Failed to update fruit.")
        }
        getFruit(fruit.id!!)
        getAllFruits()
        return success
    }

    override fun deleteFruit(fruit: Fruit): Boolean {
        val success = dbHelperModelImplementor.deleteFruit(fruit)
        if (!success) {
            throw Exception("Failed to delete fruit.")
        }
        mutableFruit.value = null
        getAllFruits()
        return success
    }
}