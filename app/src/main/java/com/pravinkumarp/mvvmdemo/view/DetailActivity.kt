package com.pravinkumarp.mvvmdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pravinkumarp.mvvmdemo.R
import com.pravinkumarp.mvvmdemo.model.bean.Fruit
import com.pravinkumarp.mvvmdemo.viewmodel.CommonViewModelImplementor

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    private lateinit var commonViewModelImplementor: CommonViewModelImplementor

    private var fruitId: Int = -1

    private lateinit var etFruitName: EditText
    private lateinit var etFruitDescription: EditText
    private lateinit var btUpdateFruit: Button
    private lateinit var btDeleteFruit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fruitId = intent.getIntExtra(EXTRA_ID, -1)

        etFruitName = findViewById(R.id.etFruitName)
        etFruitDescription = findViewById(R.id.etFruitDescription)
        btUpdateFruit = findViewById(R.id.btUpdateFruit)
        btDeleteFruit = findViewById(R.id.btDeleteFruit)

        commonViewModelImplementor = ViewModelProvider(this).get(CommonViewModelImplementor::class.java)

        commonViewModelImplementor.getFruit(fruitId).observe(this, Observer { fruit ->
            if (fruit == null) {
                etFruitName.setText("")
                etFruitDescription.setText("")
            } else {
                etFruitName.setText(fruit.name)
                etFruitDescription.setText(fruit.description)
            }
        })

        commonViewModelImplementor.getError().observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        btUpdateFruit.setOnClickListener {
            commonViewModelImplementor.updateFruit(
                Fruit(
                    fruitId,
                    etFruitName.text.toString(),
                    etFruitDescription.text.toString()
                )
            )
            Toast.makeText(this, "Updated fruit.", Toast.LENGTH_LONG).show()
        }

        btDeleteFruit.setOnClickListener {
            commonViewModelImplementor.deleteFruit(
                Fruit(
                    fruitId,
                    etFruitName.text.toString(),
                    etFruitDescription.text.toString()
                )
            )
            Toast.makeText(this, "Deleted fruit.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        commonViewModelImplementor.getFruit(fruitId).removeObservers(this)
        commonViewModelImplementor.getError().removeObservers(this)
        lifecycle.removeObserver(commonViewModelImplementor)
    }
}