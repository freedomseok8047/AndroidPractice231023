package com.example.myapp_test7_8_9_10_11_12.Public.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test7_8_9_10_11_12.Public.model.ItemModel
import com.example.myapp_test7_8_9_10_11_12.databinding.ItemRetrofit3Binding


// 매개변수에는, 목록요소의 뷰가.
// 이름 : item_retrofit.xml
// 왼쪽 : 이미지 하나, 오른쪽에 : 세로방향으로 텍스트뷰 3개 정도
class MyViewHolderRetrofit4(val binding: ItemRetrofit3Binding)
    : RecyclerView.ViewHolder(binding.root)

// 리사이클러 뷰 준비물 1)어댑터 2)뷰홀더 3)목록 요소 뷰 4)데이터
// 매개벼수 구성 1)context , 2)데이터
// datas -> [모델1,모델2,모델3,.....]
// 클래스의 주 생성자 , 클래스 명 뒤에 오는 소괄호 안에 내용 -> constructor 생략하고 씀
// val context: Context, val datas: List<UserModel>?
// 변수의 타입을
class MyAdapterRetrofit4(val context: Context, val datas: MutableList<ItemModel>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    // 틀 요소 할 때,
    // 변경 되는 부분은 목록 요소 뷰홀더 부분
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : RecyclerView.ViewHolder {
        // 목록 요소를 해당 어댑터에 적용한다.
        return MyViewHolderRetrofit4(
            ItemRetrofit3Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    // 이 코드 통으로 재사용
    override fun getItemCount(): Int {
        // ? -> 널 허용 연산자,
        // ?: -> 앨비스 연산자
        // 해당 값이 있으면 , 그값을 사용하고, :datas.size 사용하고,
        // 이게 만약 널이라고 하면 , 앨비스 연산자 ?: 다음의 기본값을 사용
        return datas?.size ?:0
    }


    // 실제로 해당 뷰에, 데이터를 연결하는 부분
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 내부에서 뷰 작업 쉽게 하려고, 뷰 홀더로 형변환 후, 바인딩 재할당
        val binding = (holder as MyViewHolderRetrofit4).binding


        // datas에 담긴 모델을 하나씩 꺼내서, 뷰에 데이터를 붙이는 작업
        // 리스트의 각 요소마다 하나씩 꺼내어, 임의의 user 변수에 담기
        val item = datas?.get(position)
        // 뷰에 데이터 내용 붙이기 1)일반 텍스트 먼저 붙이기
        binding.retrofitTitleView.text = item?.MAIN_TITLE
        binding.retrofitContent1View.text = item?.TITLE
        binding.retrofitContent2View.text = item?.TRFC_INFO



        // 방법2 glide 통해서 , 이미지를 직접 가져와서 처리하는 부분
        Glide.with(context)
            // load 실제 URL 주소 직접 넣기
            .load(item?.MAIN_IMG_NORMAL)
            .override(700,300)
            .into(binding.retrofitMainImg)

    }

}





























