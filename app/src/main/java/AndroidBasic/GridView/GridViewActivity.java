package AndroidBasic.GridView;

import AndroidExampleModel.GridView_Model;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.GridViewBinding;

/**
 * GridView 예제
 * 10번째 기초 예제
 * 여기서 부터 Naming이 적용됨.
 */
public class GridViewActivity extends BaseActivity {

    private GridViewBinding mBinding;
    final String TITLE = "GridView 예제";

    private GridViewAdapter mAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        mBinding = GridViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);


//        gridview = (GridView) findViewById(R.id.gridview);
        mAdapter = new GridViewAdapter(GridView_Model.getInstance().getGridViewItemsList());
        mBinding.gridview.setAdapter(mAdapter);


//        mBinding.gridview.
    }


}