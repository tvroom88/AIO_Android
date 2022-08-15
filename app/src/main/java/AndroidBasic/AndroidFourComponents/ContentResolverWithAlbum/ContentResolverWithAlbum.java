package AndroidBasic.AndroidFourComponents.ContentResolverWithAlbum;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.aio_android.R;

import java.util.ArrayList;


/**
 *      Gallery에서 사진을 가져오는 Activity
 *      1. Permission 받는 부분 -> AndroidManifest.xml에 필요한 permission 추가
 *      2. Content Resolver부분 추가
 *      3. startActivityForResult아 deprecated. 그래서 ActivityResultLauncher부분 추가
 */
public class ContentResolverWithAlbum extends AppCompatActivity {

    ImageView imageView;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resolver_with_album);

        imageView = findViewById(R.id.imageView);

        // 위험권한을 부여할 권한 지정 - READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        checkPermissions(permissions);
        // --- 권한부여 체크 완료 ---

        // startActivityForResult아 deprecated 됨. 그래서 추가됨
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Uri uri = intent.getData();

                            //Content Resolver를 사용해서 하는 부분
//                            ContentResolver resolver = getContentResolver();
//                            try {
//                                InputStream instream = resolver.openInputStream(uri);
//                                Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
//                                imageView.setImageBitmap(imgBitmap);
//
//                                instream.close();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }

                            // 이 짧은 코드가 위에껄 다 대체 가능. 혹시 몰라서 위에껀 남겨둠.
                            imageView.setImageURI(uri);
                        }
                    }
                });

        int permissionCheck1 = ContextCompat.checkSelfPermission(this, permissions[0]);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, permissions[1]);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (permissionCheck1 == PackageManager.PERMISSION_DENIED || permissionCheck2 == PackageManager.PERMISSION_DENIED) {
                //권한 없을때
                askPermissionAgain();
            } else {
                //권한 체크가 안되어있는 경우
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);
            }
        });
    }

    // 퍼미션을 한번 물어보고 추가 안됐을시 다시물어볼때 추가.
    public void askPermissionAgain(){
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("권한 설정")
                .setMessage("권한 거절로 인해 일부기능이 제한됩니다")
                .setPositiveButton("권한 설정하러 가기", (dialog, which) -> {
                    try{
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                .setData(Uri.parse("package:"+getPackageName()));
                        startActivity(intent);

                    } catch (ActivityNotFoundException e){
                        e.printStackTrace();;
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    // 퍼미션 체크하는 부분
    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for (int i = 0; i < permissions.length; i++) {
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " 권한 있음.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, curPermission + " 권한 없음.", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " 권한 설명 필요함.", Toast.LENGTH_SHORT).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }

        if (targetList.size() > 0) {
            String[] targets = new String[targetList.size()];
            targetList.toArray(targets);
            ActivityCompat.requestPermissions(this, targets, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for(int i=0; i<grantResults.length; i++){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한을 사용자가 승인함.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "권한이 거부됨.", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
