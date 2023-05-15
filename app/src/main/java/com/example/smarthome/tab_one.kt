package com.example.smarthome

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text


class tab_one : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.main_nav)
        navBar.visibility = View.GONE;


        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tab_one, container, false)
        val hidden_text = v.findViewById<TextView>(R.id.hidden_text)
        val noti_icon = v.findViewById<ImageView>(R.id.noti_img)
        val noti_txt = v.findViewById<TextView>(R.id.noti_txt)

        noti_icon.setOnClickListener(){
            noti_icon.visibility = View.GONE
            noti_txt.visibility = View.GONE
            hidden_text.visibility = View.VISIBLE
        }
        hidden_text.setOnClickListener(){
            inputDialog()
        }
        return v

    }
    private fun inputDialog() {

        val inputdia = AlertDialog.Builder(activity)
        val customlayout : View = layoutInflater.inflate(R.layout.add_notification,null)
        inputdia.setTitle("Enter a value")
        inputdia.setView(customlayout)
        inputdia.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
            val editText = customlayout.findViewById<EditText>(R.id.notification_input)
            val input = editText.text.toString()
            findNavController().navigate(R.id.action_select_a_thingFragment_to_thirdFragment)


        }
        inputdia.setNegativeButton("CANCEL") { dialog: DialogInterface?, which: Int ->

        }
        inputdia.show()


    }
}