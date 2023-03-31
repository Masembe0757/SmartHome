package com.example.smarthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.smarthome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(FirstFragment())
        binding.mainNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> replaceFragment(FirstFragment())
                R.id.page_2 -> replaceFragment(SecondFragment())
                R.id.page_3 -> replaceFragment(ThirdFragment())
                R.id.page_4 -> replaceFragment(FourFragment())
                R.id.page_5 -> replaceFragment(FifthFragment())
            }
            true
        }

    }

    fun replaceFragment(fragment: Fragment){
        val setScreen = supportFragmentManager
        var transaction = setScreen.beginTransaction()
        transaction.replace(R.id.lay_main, fragment)
        transaction.commit()
    }
}