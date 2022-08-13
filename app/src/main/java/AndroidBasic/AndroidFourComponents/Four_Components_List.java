package AndroidBasic.AndroidFourComponents;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.FourComponent_Model;
import AndroidExampleModel.JetPack_Model;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class Four_Components_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_components_list);

        ListView listview = findViewById(R.id.listView1);

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                FourComponent_Model.getInstance().getAndroid_four_component_list(),
                FourComponent_Model.getInstance().get_Four_Component_Class_List()
        );

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}