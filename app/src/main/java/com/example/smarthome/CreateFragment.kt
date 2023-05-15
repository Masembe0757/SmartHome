package com.example.smarthome

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.FragmentCreateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private  val args: CreateFragmentArgs by navArgs()


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
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        val cross = binding.cancel
        val EditT = binding.routineName
        val HiddenT = binding.routineNameHidden
        val toEvent = binding.toAddEvent
        val toAction = binding.toAddAction
        val tickv = binding.check1
        val tp = binding.timePlace

        val set = binding.settime
        val watch = binding.watch
        val tim = args.timeA
        val routname = args.rtname
        tp.text = tim.toString()
        EditT.hint = routname





        EditT.setOnFocusChangeListener { view, b ->
            HiddenT.visibility = View.VISIBLE

        }

        cross.setOnClickListener{
           findNavController().navigate(R.id.action_createFragment_to_selectFragment)
        }
        toEvent.setOnClickListener{
            val action = CreateFragmentDirections.actionCreateFragmentToSelectEventFragment(EditT.text.toString())
            findNavController().navigate(action)
        }
        toAction.setOnClickListener{
        findNavController().navigate(R.id.action_createFragment_to_select_a_thingFragment)
        }
        return binding.root
    }

}


