package com.example.aio_android;

import AndroidExampleList.Android_Example_List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aio_android.databinding.ActivityMainBinding;
import com.example.aio_android.databinding.LifeCycleBinding;

// 앱의 가장 첫번째 화면
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    final String title = "안드로이드 예제 앱";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        // 첫화면과 안드로이드 예제 리스트와 연결
        binding.MainActivityButton1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Android_Example_List.class);
            startActivity(intent);
        });

    }
}

