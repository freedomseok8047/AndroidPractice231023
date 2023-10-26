package com.example.myapp_test7_8_9_10_11_12.ch11_Test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test7_8_9_10_11_12.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}