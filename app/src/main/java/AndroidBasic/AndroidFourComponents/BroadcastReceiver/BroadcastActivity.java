package AndroidBasic.AndroidFourComponents.BroadcastReceiver;

import android.Manifest;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class BroadcastActivity extends AppCompatActivity {

    Button buttonSend;
    Button checkSMSButton;
    EditText textPhoneNo;
    EditText textSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);


        checkPermission();
        buttonSend = findViewById(R.id.smsSendButton1);
        checkSMSButton = findViewById(R.id.goSmsActivityButton);
        textPhoneNo = findViewById(R.id.editTextPhoneNo);
        textSMS =  findViewById(R.id.editTextSMS);

        buttonSend.setOnClickListener(v -> {
            //입력한 값을 가져와 변수에 담는다
            String phoneNo = textPhoneNo.getText().toString();
            String sms = textSMS.getText().toString();

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

        checkSMSButton.setOnClickListener(v -> {
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