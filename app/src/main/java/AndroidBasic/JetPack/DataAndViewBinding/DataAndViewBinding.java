package AndroidBasic.JetPack.DataAndViewBinding;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.DataAndViewBindingBinding;
import com.example.aio_android.databinding.JetpackBinding;

import java.util.Base64;

/**
 * DataBinding
 * (1) build.gradle에 추가
 * (2) android_data_and_view_binding.xml 확인
 *
 * ViewBinding
 * (1) build.gradle에 추가
 * (2) DataAndViewBinding.java 확인
 */

public class DataAndViewBinding extends BaseActivity {

    DataAndViewBindingBinding binding;
    final String TITLE = "데이터 & 뷰 바인딩 예제";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 첫번쨰 방법
        binding = DataAndViewBindingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, TITLE);

        // 두번째 방법
        // binding = DataBindingUtil.setContentView(this, R.layout.android_data_and_view_binding);

        //binding을 통해 유저 객체 업데이트
        binding.setUser(new User("James", 32));

        //editText를 통해 username과 age 변환
        binding.bindingButton1.setOnClickListener(v -> {
            String name = binding.bindingEdittext1.getText().toString();
            int age  = Integer.parseInt(binding.bindingEdittext2.getText().toString());
            binding.setUser(new User(name, age));
        });
    }
}
