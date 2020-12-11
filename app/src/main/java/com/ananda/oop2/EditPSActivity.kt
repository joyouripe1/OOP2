package com.ananda.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananda.oop2.Database.AppRoomDB
import com.ananda.oop2.Database.PS
import kotlinx.android.synthetic.main.activity_edit_ps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPSActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ps)
        setupListener()
    }

    fun setupListener(){
        btn_saveLaptop.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PSDao().addPS(
                    PS(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()) )
                )
                finish()
            }
        }
    }
}