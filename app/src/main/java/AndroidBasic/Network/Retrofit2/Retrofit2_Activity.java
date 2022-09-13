package AndroidBasic.Network.Retrofit2;

import AndroidBasic.Network.NetworkUtil;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.NetworkRetrofitBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * Retrofit2 예제
 * (1) REST API로 받아올 데이터를 변환하여 매핑할 DTO 클래스 선언 - data_model
 * (2) 사용할 메소드 선언 - Retrofit_interface
 * (3) Retrofit 인스턴스 생성 - retrofit_client
 */

public class Retrofit2_Activity extends BaseActivity {

    Call<data_model> call;
    Call<List<data_model>> call2;

    private NetworkRetrofitBinding binding;
    final String title = "Retrofit2 예제";

    List<data_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_retrofit);

        binding = NetworkRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        //Internet 연결 체크
        NetworkUtil.getConnectivityStatus(this);

        //데이터 크기 체크후 Edittext에 hint 적용
        getDataLength();

        binding.button.setOnClickListener(v -> {
            String id = binding.editText.getText().toString();
            getOneData(id);
        });

        binding.button2.setOnClickListener(v -> {
            getAllData();
        });

        binding.button3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(new retrofit_client().getBaseUrl() + "posts"));
            startActivity(intent);
        });
    }

    public void getOneData(String id) {
        call = retrofit_client.getApiService().test_api_get(id);
        call.clone().enqueue(new Callback<>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<data_model> call, Response<data_model> response) {
                String str;
                if (response.isSuccessful()) {
                    data_model result = response.body();
                    str = result.getID() + "\n" + result.getTitle() + "\n";
                } else {
                    // 3xx, 4xx등 통신에 실패한 경우
                    str = "오류 : " + response.code();
                }
                binding.textView1.setText(str);
            }
            @Override
            public void onFailure(Call<data_model> call, Throwable t) {

            }
        });
    }

    //모든 데이터 가져오기
    public void getAllData() {
        //clone은 여러번 사용하고 싶을을때 추가
        call2 = retrofit_client.getApiService().get();
        call2.clone().enqueue(new Callback<>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
                StringBuilder sb = new StringBuilder();
                if (response.isSuccessful()) {
                    List<data_model> result = response.body();
                    for (data_model data : result) {
                        sb.append("id : " + data.getID() + "\n");
                        sb.append("title : " + data.getTitle() + "\n\n");
                    }
                } else {
                    sb.append("오류 : ").append(response.code());
                }
                binding.textView1.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<List<data_model>> call, Throwable t) {

            }
        });
    }

    //모든 데이터 가져오기
    public void getDataLength() {
        //clone은 여러번 사용하고 싶을을때 추가
        call2 = retrofit_client.getApiService().get();
        call2.clone().enqueue(new Callback<>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
                list = response.body();
                binding.editText.setHint("0 부터 " + (list.size() - 1) + " 까지 선택하세요");
            }

            @Override
            public void onFailure(Call<List<data_model>> call, Throwable t) {
            }
        });
    }
}