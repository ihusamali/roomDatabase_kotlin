package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contactDB")
            .build()

        GlobalScope.launch {
            db.contactDao().insertContact(Contact(0, "Husam", "101010101010"))
        }
    }

    fun getData(view: View) {
        db.contactDao().getContacts().observe(this, Observer {
            Log.d("Main Activity", it.toString())
        })
//        //insert contact
//        lifecycleScope.launch(Dispatchers.IO) {
//            db.contactDao().insertContact(Contact(101, "ksdfhsjdfbs", "idbi"))
//        }
    }


}