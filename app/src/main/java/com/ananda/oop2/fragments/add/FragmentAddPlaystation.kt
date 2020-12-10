package com.ananda.oop2.fragments.add


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ananda.oop2.R
import com.ananda.oop2.Playstation
import com.ananda.oop2.PlaystationViewModel
import kotlinx.android.synthetic.main.fragment_add_ps.*
import kotlinx.android.synthetic.main.fragment_add_ps.view.*


class FragmentAddPlaystation : Fragment() {

    private  lateinit var mPlaystationViewModel: PlaystationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_ps, container, false)

        mPlaystationViewModel = ViewModelProvider( this).get(PlaystationViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun  insertDataToDatabase(){
        val durasi = add_durasi.text.toString()
        val jaminan = add_jaminan.text.toString()


        if (inputCheck(durasi, jaminan)){
            // check user object
            val playstation = Playstation ( 0, durasi, jaminan)
            // add data to Database
            mPlaystationViewModel.addPlaystation(playstation)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)


        }else{
            Toast.makeText(requireContext(),"Please fill out all field.", Toast.LENGTH_LONG).show()
        }

    }

    private  fun  inputCheck(durasi: String, jaminan: String): Boolean{
        return !(TextUtils.isEmpty(durasi) && TextUtils.isEmpty(jaminan))
    }
}
