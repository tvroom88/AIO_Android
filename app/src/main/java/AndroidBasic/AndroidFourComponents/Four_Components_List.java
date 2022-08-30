package AndroidBasic.AndroidFourComponents;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.FourComponent_Model;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
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

        binding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

    }
}