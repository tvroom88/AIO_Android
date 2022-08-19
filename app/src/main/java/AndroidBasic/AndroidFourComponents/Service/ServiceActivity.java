package AndroidBasic.AndroidFourComponents.Service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.ServiceBinding;


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
public class ServiceActivity extends BaseActivity {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static ServiceActivity mActivity;
    final String title = "Service 에제";
    private ServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        //Service 부분을 위해서
        mActivity = this;

        Intent serviceIntent = new Intent(ServiceActivity.this, ServiceA.class);

        //서비스와 연결
        binding.startBtn.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(serviceIntent);
            else startService(serviceIntent);
        });

        binding.endBtn.setOnClickListener(view -> {
            stopService(serviceIntent);
        });

    }


}