package com.example.smarthome

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.format.DateFormat
import android.view.KeyEvent.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DispatchMode
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.FragmentCreateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.*
import java.util.*

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private  val args: CreateFragmentArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("CommitPrefEdits", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.main_nav)
        navBar.visibility = View.GONE;
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        val cross = binding.cancel
        val EditT = binding.routineName
        val HiddenT = binding.routineNameHidden
        val toEvent = binding.toAddEvent
        val toAction = binding.toAddAction
        val tickv = binding.check1
        val tp = binding.timePlace
        val tp2 = binding.timePlace2
        val tp3 = binding.timePlace3
        val noti1 = binding.notione
        val noti2 = binding.notitwo


        val noti_icon = binding.noti
        val set2 = binding.settime2

        val set = binding.settime
        val watch = binding.watch
        val routname = args.rtname
        var notification = args.Notification
        val actionplcholder = binding.actionPlace

        if (routname != "Routine name") {
            val timmin = args.Timemin
            val timeH = args.TimeHour
            val is24Hours = DateFormat.is24HourFormat(requireContext())

                val meridian = if(!is24Hours) { if (timeH.toInt() < 12) "AM" else "PM" } else { if (timeH.toInt()-12 <12) "AM" else "PM"}
            tp.visibility = View.GONE
            tp3.text = "The time is $timeH:$timmin $meridian"
            tp2.visibility = View.VISIBLE
            tp3.visibility = View.VISIBLE
            EditT.hint = routname
            set.visibility = View.VISIBLE
            watch.visibility = View.VISIBLE
        }



        if(notification != "Iam the action message"){
            noti_icon.visibility = View.VISIBLE
            set2.visibility =View.VISIBLE
            actionplcholder.visibility = View.GONE
            noti1.visibility= View.VISIBLE
            noti2.text ="Send Notification : $notification"
            noti2.visibility = View.VISIBLE
            val customlayout : View = layoutInflater.inflate(R.layout.process,null)
            val builder = AlertDialog.Builder(activity)
             val processdia = builder.create()
            processdia.setTitle("Creating new Routine")
            processdia.setView(customlayout)
            processdia.show()
            val pgB = customlayout.findViewById<ProgressBar>(R.id.pgBar)


            var counter = 10
            Timer().schedule(object : TimerTask(){
                override fun run() {
                    counter++
                    pgB.progress = counter
                    if(counter== 50){
                        Timer().cancel()
                        processdia.dismiss()
                        lifecycleScope.launch(Dispatchers.Main){ findNavController().navigate(R.id.action_createFragment_to_thirdFragment) }

                    }

                }
            },0,50)



        }


        EditT.setOnFocusChangeListener { view, b ->
            HiddenT.visibility = View.VISIBLE

        }

        cross.setOnClickListener {
            findNavController().navigate(R.id.action_createFragment_to_selectFragment)
        }
        toEvent.setOnClickListener {
        if(EditT.text.toString() != "") {
            val sp: SharedPreferences =
                requireActivity().getSharedPreferences("myroutines", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            val routineT = EditT.text.toString()

            editor.putString("routine", routineT)
            editor.apply()
        }
            val action = CreateFragmentDirections.actionCreateFragmentToSelectEventFragment(EditT.text.toString())
            findNavController().navigate(action)
        }
        toAction.setOnClickListener {
            findNavController().navigate(R.id.action_createFragment_to_select_a_thingFragment)
        }
        return binding.root
    }

}


