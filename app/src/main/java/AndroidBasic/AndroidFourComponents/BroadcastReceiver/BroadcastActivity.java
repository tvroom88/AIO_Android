package AndroidBasic.AndroidFourComponents.BroadcastReceiver;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.BroadcastBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import java.util.List;

/**
 *  Broadcast Receiver 예제
 *  (1) Broadcast Receiver를 쓰려면 AndroidManifest.xml에 <receiver></receiver> 등록해야함
 *  이 Activity에서는 SMS를 보내는 부분 구현
 *  SMS를 보내면 Broadcast_SMS_Activity가 자동적으로 연결됨
 */
public class BroadcastActivity extends BaseActivity {

    final String title = "BroadCast Receiver 예제";
    private BroadcastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BroadcastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);
        // 권한 허가
        checkPermission();

        binding.smsSendButton1.setOnClickListener(v -> {
            //입력한 값을 가져와 변수에 담는다
            String phoneNo = binding.editTextPhoneNo.getText().toString();
            String sms = binding.editTextSMS.getText().toString();

            try {
                //전송
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "SMS 실패, please try again later!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        binding.goSmsActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Broadcast_SMS_Activity.class);
            startActivity(intent);
        });
    }

    private void checkPermission(){
        TedPermission.create()
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                    }
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                    }
                })
                .setDeniedMessage("권한을 허용해주세요 [Setting] > [Permission]")
                .setPermissions(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS)
                .check();
    }
}
