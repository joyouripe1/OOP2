package com.ananda.oop2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananda.oop2.Database.AppRoomDB
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.PS
import kotlinx.android.synthetic.main.activity_ps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PSActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var pSAdapter: PSAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ps)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadPS()

    }
    fun loadPS(){
        CoroutineScope(Dispatchers.IO).launch {
            val AllPS = db.PSDao().getAllPS()
            Log.d("PSActivity", "dbResponse: $AllPS")
            withContext(Dispatchers.Main) {
                pSAdapter.setData(AllPS)
            }
        }
    }

    fun setupListener() {
        btn_createUser.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        pSAdapter = PSAdapter(arrayListOf(), object : PSAdapter.OnAdapterListener {
            override fun onClick(ps: PS) {
                intentEdit(ps.id, Constant.TYPE_READ)
            }

            override fun onDelete(ps: PS){
                deleteDialog(ps)
            }

        })
        list_PS.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = pSAdapter
        }
    }

    fun intentEdit(psId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditPSActivity::class.java)
                .putExtra("intent_id", psId)
                .putExtra("intent_type", intentType)
        )
    }
    private fun deleteDialog(ps: PS) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.PSDao().deletePS(ps)
                    loadPS()
                }
            }
        }
        alertDialog.show()
    }
}