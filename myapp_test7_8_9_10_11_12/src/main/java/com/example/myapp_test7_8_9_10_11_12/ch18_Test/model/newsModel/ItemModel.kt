package com.example.myapp_test7_8_9_10_11_12.ch18_Test.model.newsModel

// 서버에서 가져올 데이터 모델링

//{
//    "status": "ok",
//    "totalResults": 39196,
//    "articles": [
//    {
//        "source": {
//        "id": null,
//        "name": "Lifehacker.com"
//    },
//        "author": "Jessica Kanzler",
//        "title": "This 7th-Gen Apple iPad Is on Sale for $280 Right Now",
//        "description": "This new, open-box items iPad is from 2019 and has 10.2\" Retina display, 3GB of RAM, and 32GB of storage, and it’s on sale for $279.99 right now (reg. $459). (New, open-box items are typically excess inventory from store shelves; they go through verification …",
//        "url": "https://lifehacker.com/this-7th-gen-apple-ipad-is-on-sale-for-280-right-now-1850931256",
//        "urlToImage": "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/5ed3c31519bf1b45f84494b06fd8e6aa.png",
//        "publishedAt": "2023-10-18T15:30:00Z",
//        "content": "This new, open-box items iPad is from 2019 and has 10.2\" Retina display, 3GB of RAM, and 32GB of storage, and its on sale for $279.99 right now (reg. $459). (New, open-box items are typically excess … [+404 chars]"
//    },
class ItemModel {
    var title : String? = null
    var content : String? = null
    var urlToImage : String? = null
}