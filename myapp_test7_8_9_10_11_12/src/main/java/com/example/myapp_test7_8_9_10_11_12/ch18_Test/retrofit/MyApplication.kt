package com.example.myapp_test7_8_9_10_11_12.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 자주 사용 될 네트위크 인터페이스를, 미리 시스템에 등록.
// 메니페스트에 등록해서,
// 앱이 실행 되면, 해당 MyApplication의 기능이
// 메모리에 등록되어 사용하기 편함
class MyApplication : Application(){

    // 1)통신에 필요한 인스턴스를 선언 및 초기화
    // 아직 초기화 안함
    val networkService : INetworkService

    // 2)통신할 서버의 URL 주소를 등록함
    val retrofit : Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 초기화 할당 하는 부분
    init {
        networkService = retrofit.create(INetworkService::class.java)
    }

}
































