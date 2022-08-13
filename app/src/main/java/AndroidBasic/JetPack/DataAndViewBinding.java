package AndroidBasic.JetPack;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.databinding.DataAndViewBindingBinding;

/**
 * DataBinding
 * (1) build.gradle에 추가
 * (2) android_data_and_view_binding.xml 확인
 *
 * ViewBinding
 * (1) build.gradle에 추가
 * (2) DataAndViewBinding.java 확인
 */

public class DataAndViewBinding extends AppCompatActivity {

    DataAndViewBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 첫번쨰 방법
        binding = DataAndViewBindingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // 두번째 방법
        // binding = DataBindingUtil.setContentView(this, R.layout.android_data_and_view_binding);

        //binding을 통해 User 객체 업데이트
        binding.setUser(new User("James", 32));


        //editText를 통해 username과 age 변환
        binding.bindingButton1.setOnClickListener(v -> {
            String name = binding.bindingEdittext1.getText().toString();
            int age  = Integer.parseInt(binding.bindingEdittext2.getText().toString());
            binding.setUser(new User(name, age));
        });
    }
}
