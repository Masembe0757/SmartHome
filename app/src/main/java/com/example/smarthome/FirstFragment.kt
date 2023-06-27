package com.example.smarthome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.ActivityMainBinding
import com.example.smarthome.databinding.FragmentFirstBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()

            }

        }

        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.main_nav)
        navBar.visibility = View.VISIBLE;

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_first, container, false)
        val butt = v.findViewById<Button>(R.id.toSelect)
        butt.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_selectFragment)
        }
        return  v

    }

}