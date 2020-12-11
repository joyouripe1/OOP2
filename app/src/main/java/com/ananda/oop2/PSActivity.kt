package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananda.oop2.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_ps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PSActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ps)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allPS = db.PSDao().getAllPS()
            Log.d("PSActivity", "dbResponse: $allPS")
        }
    }

    fun setupListener() {
        btn_createLaptop.setOnClickListener {
            startActivity(Intent(this, EditPSActivity::class.java))
        }
    }
}