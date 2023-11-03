package com.example.myapp_test6.SQLiteDB.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test6.databinding.UserItemRecyclerBinding
import com.example.myapp_test6.model.User

class MyViewHolder (val binding: UserItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

// 추가, 어댑터 매개변수에 context 를 추가하면,
// 해당 액티비티나, 프래그먼트에서 뷰 작업하기 좋음.
// 현재 사용이된 곳, glide 사진 출력 라이브러리 , with(this) -> context 사용이됨.
// 토스트 출력시 this -> context , 사용이됨.
class MultiImageAdapter (val datas: ArrayList<User>, val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            UserItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        // glide 를 이용해서, 이미지를 처리하기.
        // glide : 코루틴이 적용이 되어서, 비동기식으로 처리해서 속도도 빠르고,
        // 이미지 출력도 쉽고, 크기 조절도 쉬워요.
        // 외부 기능을 가져다 사용 할게요.
        // 데이터에서 각요소 가져오기.
        Glide.with(context).load(datas[position].profileUri)
            //크기 조절
            .override(200,200)
            // 결과 이미지 넣기
            .into(binding.profileImg)
        binding.userIdView.text = datas[position].id
        binding.userNameView.text = datas[position].name
        binding.userEmailView.text = datas[position].email
        binding.userPasswordView.text = datas[position].password
        binding.userPhoneView.text = datas[position].phone
        binding.userAddressView.text = datas[position].address

        // 이벤트 처리할 때 필요한 부분
        binding.itemRootUser.setOnClickListener {
            Log.d("ljs", "item clicked : $position")
            if (position == 0) {
                Log.d("ljs", "0번 요소 item clicked : $position")
                Toast.makeText(context,"0번 요소 item clicked : $position",Toast.LENGTH_SHORT).show()
                // 상세 페이지 이동으로 사용해도 됨.
            } else {
                Log.d("ljs", "0번 요소 외 item clicked : $position")
                Toast.makeText(context,"0번 요소 외 item clicked : $position",Toast.LENGTH_SHORT).show()
            }
        }
    }

}