package com.example.aio_android;

import AndroidExampleList.Android_Example_List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LinearLayout main_activity_button = findViewById(R.id.MainActivityButton1);
        main_activity_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Android_Example_List.class);
            startActivity(intent);
        });
    }
}

