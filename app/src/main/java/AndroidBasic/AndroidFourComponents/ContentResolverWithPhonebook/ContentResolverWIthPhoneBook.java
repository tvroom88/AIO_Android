package AndroidBasic.AndroidFourComponents.ContentResolverWithPhonebook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aio_android.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

/**
 *   전화번호 목록에서 정보를 가져오는 Activity
 *   (1) permission 받는 부분 : TedPermission 라이브러리 사용
 */

public class ContentResolverWIthPhoneBook extends AppCompatActivity {

    TextView textView;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resolver_with_phone_book);

        textView = findViewById(R.id.textView);

        // startActivityForResult이 deprecated 됨. ActivityResultLauncher 추가함
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            try {
                                Uri contactsUri = intent.getData();
                                String id = contactsUri.getLastPathSegment();
                                getContacts(id);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
             requestPermission();
        });
    }

    // permission 라이브러리를 사용. (TedPermission)
    private void requestPermission(){
        TedPermission.create()
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        chooseContacts();
                    }
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                    }
                })
                .setDeniedMessage("권한을 허용해주세요 [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                .check();
    }

    public void chooseContacts() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        launcher.launch(intent);
    }

    // getContentResolver를 이용해 연락처의 모든 정보를 읽어서 Text에 넣어주는 기능
    @SuppressLint("Range")
    public void getContacts(String id) {
        Cursor cursor = null;
        String name = "";
        try {
            cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=?",
                    new String[] { id },
                    null);

            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                textView.append("Name : " + name);
                // 모든 칼럼 이름 확인
                String columns[] = cursor.getColumnNames();
                for (String column : columns) {
                    int index = cursor.getColumnIndex(column);
                    String columnOutput = ("#" + index + " -> [" + column + "] " + cursor.getString(index));
                    textView.append(columnOutput);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}