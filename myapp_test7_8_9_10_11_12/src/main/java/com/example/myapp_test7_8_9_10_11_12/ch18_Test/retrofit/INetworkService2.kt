package com.example.myapp_test7_8_9_10_11_12.ch18_Test.retrofit

import com.example.myapp_test7_8_9_10_11_12.ch18_Test.model.newsModel.ItemListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 통신 라이브러리 : retrofit2 이용해서
// 인터페이스, 추상 메서드를 만들어서
// 레트로핏 한테 전달 : 인터페이스 통해서 전달하면,
// 여기에 정의 된 함수를 이용해서 통신을 함, CRUD 개념
interface INetworkService2 {
    // https://newsapi.org/v2/everything?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=API_KEY
    // base url : https://newsapi.org
    // base url 주소 기본값에 이어서 뒤에 추가 주소 설정 : v2/everything
    // 쿼리 설정으로 파라미터 값 설정 : ?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=API_KEY
    @GET("/v2/everything")
    fun getList(
        // 파라미터 값 설정
        // 1)q 2)from 3)sortBy 4)apiKey
        @Query("q") q: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String?,
    ) : Call<ItemListModel>

}