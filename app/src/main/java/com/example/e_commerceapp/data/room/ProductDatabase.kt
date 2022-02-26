package com.example.e_commerceapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerceapp.data.models.Products

@Database(entities = [Products::class],version = 1,exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao():ProductDao


    companion object{

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context):ProductDatabase
        {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}