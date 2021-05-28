package com.pravinkumarp.mvvmdemo

import android.app.Application
import com.pravinkumarp.mvvmdemo.model.RepositoryImplementor
import com.pravinkumarp.mvvmdemo.model.db.DBRepositoryImplementor

class MainApplication: Application() {

    companion object {
        private lateinit var dbRepositoryImplementor: DBRepositoryImplementor
        private lateinit var repositoryImplementor: RepositoryImplementor

        fun getRepositoryImplementor(): RepositoryImplementor {
            return repositoryImplementor
        }
    }

    override fun onCreate() {
        super.onCreate()

        dbRepositoryImplementor = DBRepositoryImplementor(applicationContext)
        repositoryImplementor = RepositoryImplementor(dbRepositoryImplementor)
    }
}