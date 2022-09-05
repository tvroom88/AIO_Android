package AndroidBasic.AndroidDesign;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.Android_Design_Model;
import AndroidExampleModel.Android_Lists_Model;
import AndroidExampleModel.DataBase_Model;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.AndroidDesignListBinding;
import com.example.aio_android.databinding.DataBaseListBinding;

public class AndroidDesign_List extends BaseActivity {

    private AndroidDesignListBinding mBinding;
    final String TITLE = "데이터베이스 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = AndroidDesignListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                Android_Design_Model.getInstance().get_android_design_list(),
                Android_Design_Model.getInstance().getAndroid_design_class_list_class_list()
        );

        mBinding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

    }
}