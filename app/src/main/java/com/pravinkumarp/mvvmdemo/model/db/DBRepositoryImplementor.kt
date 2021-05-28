package com.pravinkumarp.mvvmdemo.model.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.pravinkumarp.mvvmdemo.model.bean.Fruit

class DBRepositoryImplementor(context: Context) : SQLiteOpenHelper(context, "Fruits.db", null, 1),
    DBRepository {

    companion object {
        private const val TBL_FRUIT = "TBL_FRUIT";
        private const val COL_FRUIT_ID = "COL_FRUIT_ID";
        private const val COL_FRUIT_NAME = "COL_FRUIT_NAME";
        private const val COL_FRUIT_DESCRIPTION = "COL_FRUIT_DESCRIPTION";
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create fruit table
        val createFruitTableQuery = "Create Table $TBL_FRUIT (" +
                "$COL_FRUIT_ID Integer Primary Key AutoIncrement," +
                "$COL_FRUIT_NAME TEXT NOT NULL," +
                "$COL_FRUIT_DESCRIPTION TEXT NOT NULL" +
                ");"
        db?.execSQL(createFruitTableQuery);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop old fruit table
        db?.execSQL("DROP TABLE IF EXISTS $TBL_FRUIT")
    }

    override fun addFruit(fruit: Fruit): Boolean {
        val values = ContentValues();
        values.put(COL_FRUIT_NAME, fruit.name)
        values.put(COL_FRUIT_DESCRIPTION, fruit.description)

        // insert into db
        writableDatabase.insert(TBL_FRUIT, null, values)
        return true
    }

    override fun getAllFruits(): ArrayList<Fruit> {
        val fruits = ArrayList<Fruit>()
        val cursor = readableDatabase.query(TBL_FRUIT, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            fruits.add(
                Fruit(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
            )
        }

        cursor.close()
        return fruits
    }

    override fun getFruit(id: Int): Fruit? {
        var fruit: Fruit? = null
        val cursor = readableDatabase.query(TBL_FRUIT, null, "$COL_FRUIT_ID=?", arrayOf("" + id), null, null, null)

        while (cursor.moveToNext()) {
            fruit = Fruit(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            )
        }

        cursor.close()
        return fruit
    }

    override fun updateFruit(fruit: Fruit): Boolean {
        val values = ContentValues()
        values.put(COL_FRUIT_NAME, fruit.name)
        values.put(COL_FRUIT_DESCRIPTION, fruit.description)

        writableDatabase.update(TBL_FRUIT, values, "$COL_FRUIT_ID=?", arrayOf("" + fruit.id))
        return true
    }

    override fun deleteFruit(fruit: Fruit): Boolean {
        writableDatabase.delete(TBL_FRUIT, "$COL_FRUIT_ID=?", arrayOf("" + fruit.id))
        return true
    }
}