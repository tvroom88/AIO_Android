package AndroidAdvanced.MVVM;

import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.MvvmExampleActivityBinding;

/**
 * 참고 url : https://learntodroid.com/consuming-a-rest-api-using-retrofit2-with-the-mvvm-pattern-in-android/
 * 사용할 url : https://www.googleapis.com/books/v1/volumes?q=harry&inauthor=rowling
 */
public class MVVM_Example extends BaseActivity {

    private MvvmExampleActivityBinding mBinding;
    final String TITLE = "MVVM example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MvvmExampleActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

    }


}