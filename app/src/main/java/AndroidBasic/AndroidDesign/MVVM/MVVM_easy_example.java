package AndroidBasic.AndroidDesign.MVVM;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.MvvmEasyExampleBinding;

public class MVVM_easy_example extends BaseActivity {

    private MvvmEasyExampleBinding mBinding;
    final String TITLE = "MVVM pattern";

    MVVM_ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MvvmEasyExampleBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);


        setOnButtonClick();

        // 데이터 자동 업데이트
        setUptext();

    }

    public void setUptext(){
        // Get the ViewModel.
        viewModel = new ViewModelProvider(this).get(MVVM_ViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> strObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                mBinding.MVVMTextview2.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getCurrentTxt().observe(this, strObserver);
    }

    public void setOnButtonClick(){
        mBinding.MVVMButton1.setOnClickListener(v -> {
            viewModel.setCurrentTxt(mBinding.MVVMEdittext1.getText().toString());
            viewModel.getCurrentTxt();
        });
    }

}

