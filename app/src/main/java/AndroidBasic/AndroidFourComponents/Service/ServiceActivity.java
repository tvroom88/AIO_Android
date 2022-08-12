package AndroidBasic.AndroidFourComponents.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;


// Android Service : (1) foreground (2) background (3) bound
/*
    여기서는 foreground 서비스와 Notification 부분만 구현
    foreground :
    (1) <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/> - xml에 추가
    (2) ServiceA 파일 추가
    (3) ServiceActivity 파일 추가
 */

// 해결해야할 문제 :
// (1) 앱이 켜져있을때는 dismiss 버튼을 눌러도 안됨
public class ServiceActivity extends AppCompatActivity {
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);

        Intent serviceIntent = new Intent(ServiceActivity.this, ServiceA.class);

        //서비스와 연결
        Button button1 = findViewById(R.id.start_btn);
        Button button2 = findViewById(R.id.end_btn);
        TextView tv1 = findViewById(R.id.text);

        button1.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(serviceIntent);
            else startService(serviceIntent);
        });

        button2.setOnClickListener(view -> {
            stopService(serviceIntent);
        });

    }


}