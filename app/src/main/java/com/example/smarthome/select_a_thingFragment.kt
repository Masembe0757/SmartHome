package com.example.smarthome

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.service.autofill.FillEventHistory
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.FragmentThirdBinding

class select_a_thingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_select_a_thing, container, false)
        val arbck = v.findViewById<ImageView>(R.id.b2C)
        val things = v.findViewById<TextView>(R.id.THINGS)
        val scenes = v.findViewById<TextView>(R.id.SCENES)
        val routines = v.findViewById<TextView>(R.id.ROUTINES)
        val things_view = v.findViewById<View>(R.id.THINGS_VIEW)
        val scenes_view = v.findViewById<View>(R.id.SCENES_VIEW)
        val routines_view = v.findViewById<View>(R.id.ROUTINES_VIEW)
        val noticon = v.findViewById<ImageView>(R.id.NOTI)
        val hidtext = v.findViewById<TextView>(R.id.Hidden_Text)
        val notitxt = v.findViewById<TextView>(R.id.NOTI_TEXT)
        hidtext.setOnClickListener{
            inputDialog()
        }
        noticon.setOnClickListener{
            hidtext.visibility = View.VISIBLE
            noticon.visibility = View.GONE
            notitxt.visibility = View.GONE

        }
        things.setOnClickListener{
            things_view.visibility = View.VISIBLE
            scenes_view.visibility = View.GONE
            routines_view.visibility = View.GONE
            hidtext.visibility = View.GONE
            noticon.visibility = View.VISIBLE
            notitxt.visibility = View.VISIBLE


        }
        scenes.setOnClickListener{
            scenes_view.visibility = View.VISIBLE
            things_view.visibility = View.GONE
            routines_view.visibility = View.GONE
            hidtext.visibility = View.GONE
            noticon.visibility = View.GONE
            notitxt.visibility = View.GONE


        }
        routines.setOnClickListener{
            routines_view.visibility = View.VISIBLE
            things_view.visibility = View.GONE
            scenes_view.visibility = View.GONE
            hidtext.visibility = View.GONE
            noticon.visibility = View.GONE
            notitxt.visibility = View.GONE


        }
        arbck.setOnClickListener{
           findNavController().navigate(R.id.action_select_a_thingFragment_to_createFragment)
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