package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.FragmentCreateBinding

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
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        val cross = binding.cancel
        val EditT = binding.routineName
        val HiddenT = binding.routineNameHidden
        val toEvent = binding.toAddEvent
        val toAction = binding.toAddAction
        val tickv = binding.check1
        val tp = binding.timePlace

        val tim = args.timeA
        val routname = args.rtname
        tp.text = tim.toString()
        EditT.hint = routname.toString()






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


