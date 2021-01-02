package com.ananda.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ananda.oop2.Database.AppRoomDB
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.PS
import kotlinx.android.synthetic.main.activity_edit_ps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPSActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var psId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ps)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_savePS.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PSDao().addPS(
                    PS(0, txt_durasi.text.toString(), txt_jenis_jaminan.text.toString() )
                )
                finish()
            }
        }
        btn_updatePS.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PSDao().updatePS(
                    PS(psId, txt_durasi.text.toString(), txt_jenis_jaminan.text.toString() )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

                btn_updatePS.visibility = View.GONE

            }
            Constant.TYPE_READ -> {
                btn_savePS.visibility = View.GONE
                btn_updatePS.visibility = View.GONE
                getPs()
            }
            Constant.TYPE_UPDATE -> {
                btn_savePS.visibility = View.GONE
                getPs()
            }
        }
    }

    fun getPs() {
        psId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val ps =  db.PSDao().getPs( psId )[0]
            txt_durasi.setText( ps.durasi )
            txt_jenis_jaminan.setText( ps.jenis_jaminan )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}