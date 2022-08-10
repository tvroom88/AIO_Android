package com.example.aio_android;

import AndroidExampleList.Android_Example_List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

// 앱의 가장 첫번째 화면
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Button을 Custom하게 하기위해 LinearLayout으로 만듬.
        LinearLayout main_activity_button = findViewById(R.id.MainActivityButton1);

        // 첫화면과 안드로이드 예제 리스트와 연결
        main_activity_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Android_Example_List.class);
            startActivity(intent);
        });

        // 추후 시간이 될경우 안드로이드 기초 내용도 더 넣을 예정
    }
}

