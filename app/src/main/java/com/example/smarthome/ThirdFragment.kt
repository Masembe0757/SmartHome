package com.example.smarthome

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_thirdFragment_to_selectFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)






        // Inflate the layout for this fragment
        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.main_nav)
        navBar.visibility = View.VISIBLE;
        val v = inflater.inflate(R.layout.fragment_third, container, false)

        val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
            Context.MODE_PRIVATE)
        val data = spout.getString("routine","")
        val min = spout.getInt("timemin",20)
        val hour = spout.getInt("timeHour",12)

        val active = v.findViewById<TextView>(R.id.ACTIVE_STATE)
        val routines_icon = v.findViewById<ImageView>(R.id.CIRCLE)
        val no_routinetxt1 = v.findViewById<TextView>(R.id.BELOW_CIRCLE1)
        val no_routinetxt2 = v.findViewById<TextView>(R.id.BELOW_CIRCLE2)
        val rout_relative_layout = v.findViewById<RelativeLayout>(R.id.rout_position)
        val name = v.findViewById<TextView>(R.id.rout_naME)
        val editR = v.findViewById<ImageView>(R.id.editRoutine)
        val RingingT = v.findViewById<TextView>(R.id.RingingT)
        if(data!= null){
            active.visibility = View.VISIBLE
            rout_relative_layout.visibility = View.VISIBLE
            name.text = data.toString()
            routines_icon.visibility=  View.GONE
            no_routinetxt1.visibility = View.GONE
            no_routinetxt2.visibility = View.GONE

            val is24Hours = DateFormat.is24HourFormat(requireContext())
            val meridian = if(!is24Hours) { if (hour < 12) "AM" else "PM" } else { if (hour-12 <12) "AM" else "PM"}

            RingingT.text = "At $hour:$min $meridian"


        }

        editR.setOnClickListener(){





            val inputdia = AlertDialog.Builder(activity)
            val customlayout : View = layoutInflater.inflate(R.layout.add_notification,null)
            inputdia.setTitle("Edit Routine")
            inputdia.setView(customlayout)
            inputdia.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->

                val editText = customlayout.findViewById<EditText>(R.id.notification_input)
                val input = editText.text.toString()
                val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
                    Context.MODE_PRIVATE)
                val availableR = spout.getString("routine","")


                editText.hint = availableR.toString()

                if(input != "") {
                    val sp: SharedPreferences = requireActivity().getSharedPreferences("myroutines", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sp.edit()
                    editor.putString("routine", input)
                    editor.apply()
                }
                findNavController().navigate(R.id.action_thirdFragment_to_selectFragment)
            }
            inputdia.setNegativeButton("Change time") { dialog: DialogInterface?, which: Int ->

                val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
                    Context.MODE_PRIVATE)
                val min = spout.getInt("timemin",20)
                val hour = spout.getInt("timeHour",12)


                val is24Hours = DateFormat.is24HourFormat(requireContext())
                val clockformat = if(is24Hours) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
                val Picker = MaterialTimePicker.Builder().setTimeFormat(clockformat).setHour(hour).setMinute(min).build()
                Picker.show(childFragmentManager,"TAG")
                v.apply {
                    Picker.addOnPositiveButtonClickListener{
                        val sp: SharedPreferences = requireActivity().getSharedPreferences("myroutines", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sp.edit()
                        editor.putInt("timemin", Picker.minute)
                        editor.putInt("timeHour",Picker.hour)
                        editor.apply()

                        findNavController().navigate(R.id.action_thirdFragment_to_selectFragment)
                    }
                }




            }
            inputdia.show()

        }




        return v

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}