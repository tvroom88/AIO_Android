package AndroidBasic.JetPack;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.JetPack_Model;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.AndroidDesignListBinding;
import com.example.aio_android.databinding.JetpackBinding;

import java.util.Base64;

public class Jetpack_List extends BaseActivity {

    private JetpackBinding mBinding;
    final String TITLE = "데이터베이스 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = JetpackBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        ListView listview = findViewById(R.id.listView1);

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                JetPack_Model.getInstance().getJecPack_List(),
                JetPack_Model.getInstance().get_JetPack_Class_List()
        );

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}