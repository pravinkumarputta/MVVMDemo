package com.pravinkumarp.mvvmdemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pravinkumarp.mvvmdemo.R
import com.pravinkumarp.mvvmdemo.model.bean.Fruit
import com.pravinkumarp.mvvmdemo.view.adapters.MainActivityFruitListAdapter
import com.pravinkumarp.mvvmdemo.viewmodel.CommonViewModelImplementor

class MainActivity : AppCompatActivity(), MainActivityFruitListAdapter.OnFruitListItemClickListener {
    private lateinit var commonViewModelImplementor: CommonViewModelImplementor

    private lateinit var etFruitName: EditText
    private lateinit var etFruitDescription: EditText
    private lateinit var btAddFruit: Button
    private lateinit var recyclerViewFruits: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFruitName = findViewById(R.id.etFruitName)
        etFruitDescription = findViewById(R.id.etFruitDescription)
        btAddFruit = findViewById(R.id.btAddFruit)
        recyclerViewFruits = findViewById(R.id.recyclerViewFruits)
        recyclerViewFruits.layoutManager = LinearLayoutManager(this)
        recyclerViewFruits.itemAnimator = DefaultItemAnimator()

        commonViewModelImplementor = ViewModelProvider(this).get(CommonViewModelImplementor::class.java)
        lifecycle.addObserver(commonViewModelImplementor)

        commonViewModelImplementor.getAllFruits().observe(this, Observer { fruitList ->
            recyclerViewFruits.adapter = MainActivityFruitListAdapter(fruitList, this)
        })

        commonViewModelImplementor.getError().observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        btAddFruit.setOnClickListener {
            commonViewModelImplementor.addFruit(Fruit(
                    etFruitName.text.toString(),
                    etFruitDescription.text.toString()
            ))
        }
    }

    override fun onFruitListItemClicked(fruit: Fruit) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, fruit.id)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        commonViewModelImplementor.getAllFruits().removeObservers(this)
        commonViewModelImplementor.getError().removeObservers(this)
        lifecycle.removeObserver(commonViewModelImplementor)
    }
}