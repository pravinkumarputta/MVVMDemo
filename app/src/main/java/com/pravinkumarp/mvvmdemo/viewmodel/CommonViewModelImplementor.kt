package com.pravinkumarp.mvvmdemo.viewmodel

import androidx.lifecycle.*
import com.pravinkumarp.mvvmdemo.MainApplication
import com.pravinkumarp.mvvmdemo.model.bean.Fruit

class CommonViewModelImplementor : ViewModel(), CommonViewModel {
    private val repositoryImplementor = MainApplication.getRepositoryImplementor()

    private var mutableFruitList = MutableLiveData<ArrayList<Fruit>>()
    private var mutableFruit = MutableLiveData<Fruit?>()
    private val mutableError = MutableLiveData<String>()

    override fun getAllFruits(): LiveData<ArrayList<Fruit>> {
        try {
        mutableFruitList = repositoryImplementor.getAllFruits()
        } catch (ex:Exception) {
            mutableError.value = ex.message
        }
        return mutableFruitList
    }

    override fun getFruit(id: Int): LiveData<Fruit?> {
        try {
            mutableFruit = repositoryImplementor.getFruit(id)
        } catch (ex:Exception) {
            mutableError.value = ex.message
        }
        return mutableFruit
    }

    override fun getError(): LiveData<String> {
        return mutableError;
    }

    override fun addFruit(fruit: Fruit) {
        try {
           repositoryImplementor.addFruit(fruit)
        } catch (ex:Exception) {
            mutableError.value = ex.message
        }
    }

    override fun updateFruit(fruit: Fruit) {
        try {
            repositoryImplementor.updateFruit(fruit)
        } catch (ex:Exception) {
            mutableError.value = ex.message
        }
    }

    override fun deleteFruit(fruit: Fruit) {
        try {
            repositoryImplementor.deleteFruit(fruit)
        } catch (ex:Exception) {
            mutableError.value = ex.message
        }
    }

    override fun refreshData(owner: LifecycleOwner) {
        getAllFruits()
    }
}