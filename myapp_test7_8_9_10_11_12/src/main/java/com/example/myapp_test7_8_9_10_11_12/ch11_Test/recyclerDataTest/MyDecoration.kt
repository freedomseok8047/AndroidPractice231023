package com.example.myapp_test_7_8_9_10_11_12.ch11_Test.recyclerDataTest

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test7_8_9_10_11_12.R

// 리사이클러 뷰에, 구분선 넣기, 이미지 목록 요소를 기준으로 , 전, 후 넣기.

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

    // 목록 요소가 출력되기 전에, 이미지를 먼저 출력함. (배경으로 사용됨)
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(// 안드로이드 , 이미지 타입을 비트맵 또는 드로어블 타입을 지정
            // 배경으로 사용할 이미지 하나
            // context -> 액티비티, 프래그먼트의 화면을 가리킬 때 많이 사용함.
            BitmapFactory.decodeResource(context.getResources(), R.drawable.img1),
            0f,
            0f,
            null
        );
    }
    // 목록 요소가 출력 후에, 이미지를 늦게 출력함. (전경(foreground)로 사용됨)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //뷰 사이즈 계산
        val width = parent.width
        val height = parent.height
        //이미지 사이즈 계산
        val dr: Drawable? =
            // 포그라운드로 사용할 이미지 하나
            ResourcesCompat.getDrawable(context.getResources(), R.drawable.india, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight
        //이미지가 그려질 위치 계산 -> 정 중앙을 계산 식으로 표현
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 - drHeight?.div(2) as Int
        c.drawBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.india),
            left.toFloat(),
            top.toFloat(),
            null
        )

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1

        // 목록 요소의 갯수가 3개씩
        if (index % 3 == 0) //left, top, right, bottom
            // 3의 배수이면 bottom에 공간을 줌 -> 60
            outRect.set(10, 10, 10, 60)
        else
            outRect.set(10, 10, 10, 0)

        view.setBackgroundColor(Color.WHITE)
        // 뷰의 높이 (Z축:화면에 90도 방향) 를 픽셀 단위로 조정, 즉 앞으로 띄우기
        // 흰색 배경과 흰색 뷰 배경이면 효과가 잘 보임
        ViewCompat.setElevation(view, 500.0f)

    }
}