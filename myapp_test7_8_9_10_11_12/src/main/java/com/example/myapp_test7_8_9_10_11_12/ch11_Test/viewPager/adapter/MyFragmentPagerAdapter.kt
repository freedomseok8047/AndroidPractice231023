package com.example.myapp_test7_8_9_10_11_12.ch11_Test.viewPager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp_test7_8_9_10_11_12.ch11_Test.viewPager.fragment.SampleFrag1
import com.example.myapp_test7_8_9_10_11_12.ch11_Test.viewPager.fragment.SampleFrag2
import com.example.myapp_test7_8_9_10_11_12.ch11_Test.viewPager.fragment.SampleFrag3

// 뷰 페이저의 데이터를 연결시켜주는, 프래그먼트 형식의 어댑터
class MyFragmentPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    var smapleFragment: List<Fragment>

    init {
        // SampleFrag1-3 -> 만들 예정
        smapleFragment = listOf(SampleFrag1(), SampleFrag2(), SampleFrag3())

    }

    override fun getItemCount(): Int = smapleFragment.size

    override fun createFragment(position: Int): Fragment {
        val returnFrament : Fragment = smapleFragment[position]
        return  returnFrament
    }

}