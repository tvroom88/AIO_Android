package AndroidBasic.AndroidFourComponents;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class Android_Four_Components_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_four_components_list);

        ListView listview = findViewById(R.id.listView1);
        Android_Four_Components_Adapter adapter = new Android_Four_Components_Adapter();

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}