package AndroidBasic.AndroidFourComponents;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.FourComponent_Model;
import AndroidExampleModel.JetPack_Model;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.ExampleListBinding;
import com.example.aio_android.databinding.FourComponentsListBinding;

public class Four_Components_List extends BaseActivity {

    private FourComponentsListBinding binding;
    final String title = "4대 컴포넌트 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FourComponentsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                FourComponent_Model.getInstance().getAndroid_four_component_list(),
                FourComponent_Model.getInstance().get_Four_Component_Class_List()
        );

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        binding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}