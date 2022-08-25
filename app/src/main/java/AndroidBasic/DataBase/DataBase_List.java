package AndroidBasic.DataBase;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.DataBase_Model;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.DataBaseListBinding;

/**
 * 데이터베이스 예제 리스트
 * (1) SQLite (2) Room DataBase
 */
public class DataBase_List extends BaseActivity {

    private DataBaseListBinding binding;
    final String title = "데이터베이스 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBaseListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                DataBase_Model.getInstance().get_database_list(),
                DataBase_Model.getInstance().get_database_class_list()
        );

        binding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

    }

}