package com.example.smarthome

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.FragmentSelectEventBinding
import com.example.smarthome.databinding.FragmentThirdBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class selectEventFragment : Fragment() {
    private  lateinit var binding: FragmentSelectEventBinding
    private val args : selectEventFragmentArgs by  navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectEventBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_select_event, container, false)
        val bck = v.findViewById<ImageView>(R.id.back_to_create)
        val timetap = v.findViewById<TextView>(R.id.select_time)





        timetap.setOnClickListener{
            timePicker()
        }

        bck.setOnClickListener{

            findNavController().navigate(R.id.action_selectEventFragment_to_createFragment)
        }
        return v

    }
    @SuppressLint("SetTextI18n")
    fun timePicker(){
        val is24Hours = is24HourFormat(requireContext())
        val clockformat = if(is24Hours) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
        val Picker = MaterialTimePicker.Builder().setTimeFormat(clockformat).setHour(12).setMinute(0).build()
        Picker.show(childFragmentManager,"TAG")

            binding.apply {

                Picker.addOnPositiveButtonClickListener{
                val direction = selectEventFragmentDirections.actionSelectEventFragmentToCreateFragment("Date & Time \n The time is "+Picker.hour+":"+Picker.minute,args.rtname2)
                findNavController().navigate(direction)


                }


                }

            }

}