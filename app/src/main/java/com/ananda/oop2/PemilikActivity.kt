package com.ananda.oop2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pemilik.*

class PemilikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemilik)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("items")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                val dataArray = ArrayList<Pemilik>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Pemilik::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListPemilik.adapter = PemilikAdapter(dataArray, object : PemilikAdapter.OnClick {
                    override fun edit(pemilik: Pemilik?) {
                        val intent = Intent(this@PemilikActivity, FormPemilikActivity::class.java)
                        intent.putExtra("pemilik", pemilik)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@PemilikActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddPemilik.setOnClickListener {
            startActivity(Intent(this@PemilikActivity, FormPemilikActivity::class.java))
        }
    }
}