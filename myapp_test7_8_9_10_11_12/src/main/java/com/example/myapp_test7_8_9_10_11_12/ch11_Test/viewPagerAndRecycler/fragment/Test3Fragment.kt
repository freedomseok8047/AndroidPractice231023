package com.example.myapp_test7_8_9_10_11_12.ch11_Test.viewPagerAndRecycler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test7_8_9_10_11_12.databinding.FragmentTest3Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Test3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Test3Fragment : Fragment() {
    lateinit var binding: FragmentTest3Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTest3Binding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTest3Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}