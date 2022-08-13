package AndroidBasic.JetPack;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.JetPack_Model;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class Jetpack_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack);

        ListView listview = findViewById(R.id.listView1);

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                JetPack_Model.getInstance().getJecPack_List(),
                JetPack_Model.getInstance().get_JetPack_Class_List()
        );

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}