package AndroidBasic.Network;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.Network_Model;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.NetworkListBinding;

/**
 * 네트워크 통신 리스트
 * (1) HTTPURLConnetions
 * (2) Okhttp3
 * (3) Retrofit2
 */
public class Network_List extends BaseActivity {

    private NetworkListBinding binding;
    final String title = "네트워크 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_list);

        binding = NetworkListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                Network_Model.getInstance().get_network_list(),
                Network_Model.getInstance().get_network_class_list()
        );

        binding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);
    }


}