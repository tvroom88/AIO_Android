package AndroidBasic.AndroidDesign.MVP;

import android.os.Bundle;
import android.view.View;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.MvpEasyExampleBinding;

public class MVP_easy_example extends BaseActivity implements Contract.View {

    private MvpEasyExampleBinding mBinding;
    final String TITLE = "MVP pattern";

    // creating object of Presenter interface in Contract
    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MvpEasyExampleBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        // instantiating object of Presenter Interface
        presenter = new Presenter(this, new Model());

        // 버튼이 눌려지면 showProgress()가 처음에 보여주다가 setText 이후 hideProgress를 불러온다.
        mBinding.button.setOnClickListener(v -> presenter.onButtonClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    // method to display the Course Detail TextView
    public void showProgress() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mBinding.textView.setVisibility(View.INVISIBLE);
    }

    @Override
    // method to hide the Course Detail TextView
    public void hideProgress() {
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.textView.setVisibility(View.VISIBLE);
    }

    @Override
    // method to set random string in the Course Detail TextView
    public void setString(String string) {
        mBinding.textView.setText(string);
    }

}
