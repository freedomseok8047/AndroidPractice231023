package com.example.myapp_test7_8_9_10_11_12.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityTest11RecyclerViewBinding

// 반드시, 베이스가 있어야함 , 액티비티 파일 (도화지)
class Test11_RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityTest11RecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTest11RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 더미 데이터로 텍스트만 임시로 사용,
        // 공공데이터, 사용자 정의한 데이터르 ㄹ가지고 와서,
        // 원하는 뷰에 데이터를 넣는 , 바인딩 작업을 함
        val datas = mutableListOf<String>()
        for(i in 1..10) {
            datas.add("더미 $i")
        }
        // 기본 값으로 세로 방향 출력.
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //---------------------------------------------------------------------------------------------------------
        // 가로 방향으로 출력 해보기.
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.recyclerView.layoutManager = layoutManager
        // 액티비티 <-> 뷰 데이터 연결 시켜 주는 어뎁터 = model.attribute
        // 어뎁터에 data 집어넣기
        binding.recyclerView.adapter = MyAdapter(datas)

        // 구분선 넣기, 나중에 옵션으로 배경이미지도 넣기 가능.
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL))

        //---------------------------------------------------------------------------------------------------------

        // 2번째 리사이클러 뷰 출력해보기. 기존 데이터 재사용, 뷰홀더, 마이 어댑터 재사용해보기.
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        // 기존 재사용
        binding.recyclerView2.adapter = MyAdapter(datas)
        binding.recyclerView2.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        //---------------------------------------------------------------------------------------------------------
        // 다시 가로
        val layoutManager2 = LinearLayoutManager(this)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        binding.recyclerView3.layoutManager = layoutManager2
        binding.recyclerView3.adapter = MyAdapter(datas)
        binding.recyclerView3.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL))
        //---------------------------------------------------------------------------------------------------------
        // 다시 세로
        binding.recyclerView4.layoutManager = LinearLayoutManager(this)
        binding.recyclerView4.adapter = MyAdapter(datas)
        binding.recyclerView4.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

    }
}