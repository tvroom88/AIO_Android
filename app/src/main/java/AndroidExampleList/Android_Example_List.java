package AndroidExampleList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.R;

import java.util.List;

public class Android_Example_List extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    Android_Example_List_Adapter mAdapter = null;
    Android_Example_List_Model model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_example_list);

        mRecyclerView = findViewById(R.id.android_example_list_recycler1);
        model = new Android_Example_List_Model();
        List<Android_Example_Item> mList = model.getData();

        mAdapter = new Android_Example_List_Adapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        mAdapter.notifyDataSetChanged();

    }
}