package AndroidBasic.IntentAndBundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.IntentResultBinding;

/**
 * Intent와 Bundle 예제
 * (1) 명시적 Intent
 * (2) 암시적 Intent
 * (3) Bundle
 */
public class IntentResultActivity extends BaseActivity {

    IntentResultBinding binding;
    final String title = "인텐트 번들 결과";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = IntentResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Toolbar 세팅
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        Intent sendIntent = new Intent();
        Intent getIntent = getIntent();
        String str = getIntent.getStringExtra("title");
        binding.resText1.setText(str);

        binding.resbutton1.setOnClickListener(v -> {
            sendIntent.putExtra("title", "응답 잘 받아왔습니다");
            setResult(RESULT_OK, sendIntent);
            finish();
        });

        processIntent(getIntent);

    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable("data");
            if (data != null) {
                binding.resText1.setText("전달 받은 데이터\nNumber : " + data.number
                        + "\nMessage : " + data.message + "\nMessage : " + data.message2);
            }
        }
    }
}