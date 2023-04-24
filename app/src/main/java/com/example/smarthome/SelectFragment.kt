package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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

        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_select, container, false)
        val butt = v.findViewById<Button>(R.id.CreatGo)
        val arrowB = v.findViewById<ImageView>(R.id.back)
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