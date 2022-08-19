package AndroidBasic.IntentAndBundle;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.IntentAndBundleBinding;

public class IntentAndBundleActivity extends BaseActivity {

    IntentAndBundleBinding binding;
    final String title = "인텐트 번들 예제";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntentAndBundleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Toolbar 세팅
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);
    }

}