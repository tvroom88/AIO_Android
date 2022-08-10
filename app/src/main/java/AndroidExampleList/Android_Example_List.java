package AndroidExampleList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.R;

/**
 * 안드로이드 예제의 전체 리스트를 보여주는 Activity
 * RecyclerView를 적용시켜 보여줍니다.
 *
 * 구성 : (1) Android_Example_List.java -> 현재 Activity, RecyclerView 포함
 * (2) Android_Example_Item.java -> RecyclerView의 각 item에 들어갈 내용을 가지고 있는 class
 * (3) Android_Example_List_Adapter.java -> RecyclerView를 위한 ViewHolder 부분을 포함하고 class.
 * (4) Android_Lists_Model.java : android_example_code_list 데이터를 사용.
 *
 */

public class Android_Example_List extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    Android_Example_List_Adapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_example_list);

        mRecyclerView = findViewById(R.id.android_example_list_recycler1);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mAdapter = new Android_Example_List_Adapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mAdapter.notifyDataSetChanged();

    }
}