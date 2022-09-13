package AndroidAdvanced;

import AndroidExampleList.Android_Example_List_Adapter;
import AndroidExampleModel.Android_Lists_Model;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.AndroidAdvancedListBinding;

public class AndroidAdvanced_List extends BaseActivity {

    RecyclerView mRecyclerView = null;
    Android_Example_List_Adapter mAdapter = null;
    private AndroidAdvancedListBinding mBinding;
    final String TITLE = "안드로이드 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = AndroidAdvancedListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        mRecyclerView = mBinding.androidExampleListRecycler1;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new Android_Example_List_Adapter(
                Android_Lists_Model.getInstance().getAndroid_advanced_code_list(),
                Android_Lists_Model.getInstance().get_Android_Advanced_Class_List()
        );

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mAdapter.notifyDataSetChanged();

    }
}