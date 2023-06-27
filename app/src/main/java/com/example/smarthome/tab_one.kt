package com.example.smarthome

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text
import java.util.Timer
import java.util.TimerTask


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

            val inputdia = AlertDialog.Builder(activity)
            val customlayout : View = layoutInflater.inflate(R.layout.add_notification,null)
            inputdia.setTitle("Enter notification text here")
            inputdia.setView(customlayout)
            inputdia.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->

                val editText = customlayout.findViewById<EditText>(R.id.notification_input)
                val input = editText.text.toString()

                if(input != "") {
                    val sp: SharedPreferences =
                        requireActivity().getSharedPreferences("myroutines", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sp.edit()
                    editor.putString("notification", input)
                    editor.apply()
                }

                val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
                    Context.MODE_PRIVATE)
                val timemin = spout.getInt("timemin",20)
                val timeHour = spout.getInt("timeHour",12)
                val rut = spout.getString("routine","")
                val action = select_a_thingFragmentDirections.actionSelectAThingFragmentToCreateFragment(Notification = input, Timemin = timemin.toString(), TimeHour = timeHour.toString(), rtname = rut.toString())
                findNavController().navigate(action)



            }
            inputdia.setNegativeButton("CANCEL") { dialog: DialogInterface?, which: Int ->

            }
            inputdia.show()

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

            if(input != "") {
                val sp: SharedPreferences =
                    requireActivity().getSharedPreferences("myroutines", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sp.edit()
                editor.putString("notification", input)
                editor.apply()
            }

            val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
                Context.MODE_PRIVATE)
            val timemin = spout.getString("timemin","")
            val timeHour = spout.getString("timeHour","")
            val rut = spout.getString("routine","")
            val action = select_a_thingFragmentDirections.actionSelectAThingFragmentToCreateFragment(Notification = input, TimeHour = timeHour.toString(), Timemin = timemin.toString(), rtname = rut.toString())
            findNavController().navigate(action)


        }
        inputdia.setNegativeButton("CANCEL") { dialog: DialogInterface?, which: Int ->

        }
        inputdia.show()


    }
}