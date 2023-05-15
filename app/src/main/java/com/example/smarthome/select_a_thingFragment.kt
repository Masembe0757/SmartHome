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
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.smarthome.databinding.FragmentThirdBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class select_a_thingFragment : Fragment() {
    private  lateinit var adapter: FragmentPageAdapter

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
        val v = inflater.inflate(R.layout.fragment_select_a_thing, container, false)
        val arbck = v.findViewById<ImageView>(R.id.b2C)
        val tablay_out = v.findViewById<TabLayout>(R.id.tab)
        val viewpager = v.findViewById<ViewPager2>(R.id.widget)
        adapter = FragmentPageAdapter(childFragmentManager,lifecycle)
        viewpager.adapter = adapter
         tablay_out.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
             override fun onTabSelected(tab: TabLayout.Tab?) {
                 if (tab != null) {
                     viewpager.currentItem = tab.position
                 }
             }

             override fun onTabUnselected(tab: TabLayout.Tab?) {

             }

             override fun onTabReselected(tab: TabLayout.Tab?) {

             }
         })
        viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tablay_out.selectTab(tablay_out.getTabAt(position))
            }
        })


        arbck.setOnClickListener{
           findNavController().navigate(R.id.action_select_a_thingFragment_to_createFragment)
    }
        return v

    }
}