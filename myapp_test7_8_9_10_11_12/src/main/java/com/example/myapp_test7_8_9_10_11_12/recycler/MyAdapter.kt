package com.example.myapp_test7_8_9_10_11_12.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test7_8_9_10_11_12.databinding.ItemRecyclerBinding

// 뷰를 모아둔 박스 -> 목록 요소의 뷰
// 주 생성자 사용했고 constructor 생략 되어있음
class MyViewHolder (val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

// 뷰와 데이터를 연결한다
// 리사이클러 뷰에서, 뷰 페이저2(캐러셀) 에서도 같은 패턴으로 사용할 예정
// 지금은 더비 데이터(datas), 나중엔 공공 데이터 내지, 백에 연결된 데이터
class MyAdapter (val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // 리사이클러 뷰의 어댑터를 상속 받으면, 필수적으로 재정의 해야하는 함수들 3개
    // 자동 완정으로 생성했음

    // 뷰 생성 해주는 부분
    // inflate : 뷰에서 객체들을 인스턴스 화
    // 리사이클러의 부모(parent) = 액티비티
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder(ItemRecyclerBinding.inflate(
        LayoutInflater.from(parent.context),parent,false))

    // 출력할 목록 아이템의 크기 (사이즈), 더미 데이터를 사용할 에정
    override fun getItemCount(): Int {
        Log.d("ljs","getItemCount : ${datas.size}")
        return datas.size
    }

    // 뷰에 데이터를 연동(바인딩) 해주는 작업
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("ljs","onBindViewHolder: $position")
        val binding = (holder as MyViewHolder).binding
        // 뷰 데이터 출력
        binding.testText.text = datas[position]
        binding.itemRoot.setOnClickListener{
            Log.d("ljs","item clicked : $position")
//            Toast.makeText(this@MyAdapter)
        }
    }
}

// 목록 아이템의 요소 뷰를 만들기