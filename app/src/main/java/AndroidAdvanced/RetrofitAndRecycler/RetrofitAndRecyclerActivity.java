package AndroidAdvanced.RetrofitAndRecycler;

import AndroidAdvanced.RetrofitAndRecycler.Adapter.RecyclerViewAdapterOne;
import AndroidAdvanced.RetrofitAndRecycler.DB.PicsumDB;
import AndroidAdvanced.RetrofitAndRecycler.DB.Picsum_Model;
import AndroidAdvanced.RetrofitAndRecycler.Retrofit.PicusumAPIClient;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.RetrofitAndRecyclerBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class RetrofitAndRecyclerActivity extends BaseActivity {

    private RetrofitAndRecyclerBinding mBinding;
    final String TITLE = "Retrofit + Recycler + Room";

    // Retrofit
    Call<List<Picsum_Model>> call2;

    // RoomDatabase 불러오기
    private PicsumDB picsumDB;

    // RoomDB에서 불러올 picusmList
    List<Picsum_Model> picsumList = new ArrayList<>();

    // RecyclerView 부분
    RecyclerView mRecyclerView = null;
    RecyclerViewAdapterOne mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = RetrofitAndRecyclerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        picsumDB = PicsumDB.getInstance(this);


        mRecyclerView = mBinding.recyclerView;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        try {
            getDataFromRoom();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Retrofit을 이용해 모든 데이터 가져오기
    public void getAllData() {
        //clone은 여러번 사용하고 싶을을때 추가
        Call<List<Picsum_Model>> call2 = PicusumAPIClient.getApiService().getPicsumList();
        call2.clone().enqueue(new Callback<>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<Picsum_Model>> call, Response<List<Picsum_Model>> response) {

                if (response.isSuccessful()) {
                    List<Picsum_Model> result = response.body();

                    try {
                        insertDataToRoom(result);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if(picsumList.size() == 0){
                        // --------- RecyclerView 부분 ---------
                        mAdapter = new RecyclerViewAdapterOne(result); // 100개의 객체를 추가시킨 리스트를 넘겨줍니다.
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                        mAdapter.notifyDataSetChanged();
                    }

                } else {
//                    sb.append("오류 : ").append(response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Picsum_Model>> call, Throwable t) {

            }
        });
    }

    public void insertDataToRoom(List<Picsum_Model> result) throws InterruptedException {
//        picsumDB.picsumDao().insertAll(result.toArray(new Picsum_Model[0]));
        ExampleThread insertThread = new ExampleThread(1, result);
        insertThread.start();
        insertThread.join();

    }

    private void getDataFromRoom() throws InterruptedException {
        ExampleThread getAllThread = new ExampleThread(2);
        getAllThread.start();
        getAllThread.join();
    }

    private void deleteAllDataFromRoom() throws InterruptedException {
        ExampleThread deleteThread = new ExampleThread(3); // 지우기
        deleteThread.start();
        deleteThread.join();
    }

    private StringBuilder ListToString(List<Picsum_Model> list){
        StringBuilder sb = new StringBuilder();
        for (Picsum_Model picsum_data : list) {
            sb.append("id : " + picsum_data.getId()  + " Author :" + picsum_data.getAuthor()+ "\n");
        }
        return sb;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PicsumDB.destroyInstance();
        picsumDB = null;
    }

    private class ExampleThread extends Thread {

        private int flag;
        List<Picsum_Model> result = null;

        private ExampleThread(int flag) { this.flag = flag;}
        private ExampleThread(int flag,  List<Picsum_Model> result) {
            this.flag = flag;
            this.result = result;
        }
        public void run() {
            if(flag == 1 && result != null){
                // insert All Data to Room DB
                picsumDB.picsumDao().insertAll(result.toArray(new Picsum_Model[0])); //insert
            }else if(flag == 2){
                // get All data from Room DB
                picsumList = picsumDB.picsumDao().getAll(); //get
                //picsumList 이게 비어있으면 바로 RecycleView를 Room DB꺼로 가져오기
                if (picsumList != null) {
                    mAdapter = new RecyclerViewAdapterOne(picsumList);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                    mAdapter.notifyDataSetChanged();
                }
                getAllData();

            }else if(flag == 3){
                // delete All data from Room DB
                picsumDB.picsumDao().deleteAll(); // delete
            }
        }
    }
}