package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_pemilik.*

class FormPemilikActivity : AppCompatActivity() {
    var pemilik: Pemilik? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pemilik)

        val data = intent.getSerializableExtra("pemilik")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("items")

        if (data != null) {
            pemilik = data as Pemilik
            etPemilikNameEdit.setText(pemilik?.nama)
            etPemilikDescriptionEdit.setText(pemilik?.id)

            btActForm.setText("Edit")
        } else {
            btActForm.setText("Tambah")
            edit = false
        }

        btActForm.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("nama", etPemilikNameEdit.text.toString())
                changeData.put("id", etPemilikDescriptionEdit.text.toString())

                myRef.child(pemilik?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, PemilikActivity::class.java))
            } else {
                val key = myRef.push().key

                val newPemilik = Pemilik()
                newPemilik.nama = etPemilikNameEdit.text.toString()
                newPemilik.id = etPemilikDescriptionEdit.text.toString()

                myRef.child(key.toString()).setValue(newPemilik)
                finish()
                startActivity(Intent(this, PemilikActivity::class.java))
            }
        }
    }
}