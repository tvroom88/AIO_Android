package AndroidBasic.JetPack.PagingWithoutLibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.PagingActivityBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.ArrayList;

public class PagingActivity extends BaseActivity {

    private PagingActivityBinding mBinding;
    final String TITLE = "Paging 예제 1";

    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    ArrayList<MainData> dataArrayList = new ArrayList<>();
    MainAdapter adapter;


    // 1페이지에 10개씩 데이터를 불러온다
    int page = 1, limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = PagingActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        nestedScrollView = mBinding.scrollView;
        recyclerView = mBinding.recyclerView;
        progressBar = mBinding.progressBar;

        adapter = new MainAdapter(PagingActivity.this, dataArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getData(page, limit);

//        recyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            {
//                page++;
//                progressBar.setVisibility(View.VISIBLE);
//                getData(page, limit);
//            }
//        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener()
        {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
            {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())
                {
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData(page, limit);
                }
            }
        });
    }

    private void getData(int page, int limit) {
        // 레트로핏 초기화
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call<String> call = mainInterface.string_call(page, limit);
        call.clone().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response.body());
                        parseResult(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("에러 : ", "에러");
            }
        });
    }


    private void parseResult(JSONArray jsonArray)
    {
        for (int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MainData data = new MainData();
                data.setImage(jsonObject.getString("download_url"));
                data.setName(jsonObject.getString("author"));
                dataArrayList.add(data);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            adapter = new MainAdapter(PagingActivity.this, dataArrayList);
            recyclerView.setAdapter(adapter);
        }
    }
}