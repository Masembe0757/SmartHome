package com.example.smarthome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.ActivityMainBinding
import com.example.smarthome.databinding.FragmentFirstBinding
import com.example.smarthome.databinding.FragmentSelectBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.zip.Inflater


class SelectFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.main_nav)
        navBar.visibility = View.GONE;
        val spout : SharedPreferences = requireActivity().applicationContext.getSharedPreferences("myroutines",
            Context.MODE_PRIVATE)
        val data = spout.getString("routine","")
        val min = spout.getInt("timemin",20)
        val hour = spout.getInt("timeHour",12)


        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_select, container, false)
        val butt = v.findViewById<Button>(R.id.CreatGo)
        val arrowB = v.findViewById<ImageView>(R.id.back)
        val rout_relative_layout = v.findViewById<RelativeLayout>(R.id.rout_position)
        val name = v.findViewById<TextView>(R.id.rout_naME)
        val RingT = v.findViewById<TextView>(R.id.RingT)







        if(data!= null){
            rout_relative_layout.visibility = View.VISIBLE
            name.text = data.toString()
            val is24Hours = DateFormat.is24HourFormat(requireContext())
            val meridian = if(!is24Hours) { if (hour < 12) "AM" else "PM" } else { if (hour-12 <12) "AM" else "PM"}
            RingT.text = "At $hour:$min $meridian"

        }

        butt.setOnClickListener{
           findNavController().navigate(R.id.action_selectFragment_to_createFragment)
        }

        arrowB.setOnClickListener{
            val fstf = FirstFragment();
            findNavController().navigate(R.id.action_selectFragment_to_firstFragment)

        }
        return  v
    }


}