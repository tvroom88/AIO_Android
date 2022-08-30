package AndroidBasic.IntentAndBundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.IntentAndBundleBinding;

public class IntentAndBundleActivity extends BaseActivity {

    IntentAndBundleBinding binding;
    final String title = "인텐트 번들 예제";
    public static final int REQUEST_CODE_MENU = 101;

    ActivityResultLauncher<Intent> launcher;
    int num1;
    int num2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntentAndBundleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Toolbar 세팅
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);
        num1 = 0;
        num2 = savedInstanceState != null ? savedInstanceState.getInt("num") : 0;

        init();
    }

    private void init() {

            launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // Add same code that you want to add in onActivityResult method
                    if (result.getResultCode() == RESULT_OK) {
                        String str = result.getData().getStringExtra("title");
                        Toast.makeText(getApplicationContext(), "응답으로 전달된 내용 : " + str,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "onActivityResult 메소드 호출됨. 요청 코드 : " + result.getResultCode() +
                                        ", 결과 코드 : " + result.getResultCode(), Toast.LENGTH_LONG).show();
                    }
                });

        binding.intentButton1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), IntentResultActivity.class);
            intent.putExtra("title", binding.intentEditText1.getText().toString());
            launcher.launch(intent);
        });

        binding.intentButton2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), IntentResultActivity.class);
            String getStrFromEditText = binding.intentEditText1.getText().toString();
            SimpleData data = new SimpleData(1, getStrFromEditText,"Parcelable을 이용해 객체 형식으로 메시지가 넘어왔습니다");
            intent.putExtra("data", data);
            launcher.launch(intent);
        });

        binding.intentButton3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
            startActivity(intent);
        });

        binding.intentButton4.setOnClickListener(v -> {
            String tel = "tel:" ;
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(tel));
            startActivity(intent);

        });
        binding.number1.setText(String.valueOf(num1));

        binding.number2.setText(String.valueOf(num2));
        binding.minus.setOnClickListener(v -> {
            binding.number1.setText(String.valueOf(--num1));
            binding.number2.setText(String.valueOf(--num2));
        });

        binding.plus.setOnClickListener(v -> {
            binding.number1.setText(String.valueOf(++num1));
            binding.number2.setText(String.valueOf(++num2));
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        // 데이터 저장
        outState.putInt("num", num2);
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle inState) {
        // 데이터 불러오기
        super.onRestoreInstanceState(inState);
    }
}
