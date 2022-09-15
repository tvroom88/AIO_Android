package AndroidAdvanced.MVVM;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.RetrofitAndRecyclerBinding;

public class MVVM_Example extends BaseActivity {

//    private M mBinding;
    final String TITLE = "MVVM example";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvvm_example_activity);

//        mBinding = RetrofitAndRecyclerBinding.inflate(getLayoutInflater());
//        setContentView(mBinding.getRoot());
//
//        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);
    }
}