package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananda.oop2.Database.AppRoomDB
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.User
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allUser = db.userDao().getAllUser()
            Log.d("UserActivity", "dbResponse: $allUser")
            withContext(Dispatchers.Main) {
                userAdapter.setData(allUser)
            }
        }
    }

    fun setupListener() {
        btn_createUser.setOnClickListener {
            startActivity(Intent(this, EditUserActivity::class.java))
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }
    fun setupRecyclerView() {
        userAdapter = UserAdapter(arrayListOf(), object: UserAdapter.OnAdapterListener {
            override fun onClick(user: User) {
                intentEdit(user.id, Constant.TYPE_READ)
            }

        })
        list_user.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = userAdapter
        }
    }

    fun intentEdit(userId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditUserActivity::class.java)
                .putExtra("intent_id", userId)
                .putExtra("intent_type", intentType)
        )
    }

}