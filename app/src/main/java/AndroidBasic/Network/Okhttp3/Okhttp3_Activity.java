package AndroidBasic.Network.Okhttp3;

import AndroidBasic.Network.NetworkUtil;
import AndroidBasic.Network.Retrofit2.data_model;
import AndroidBasic.Network.Retrofit2.retrofit_client;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.NetworkOkhttp3Binding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Okhttp3 예제:
 * (1) Thread와 JsonArray, JsonObject를  사용한 부분 구현
 * (2) Callback과 Gson을 사용한 부분 구현
 */
public class Okhttp3_Activity extends BaseActivity {

    private NetworkOkhttp3Binding binding;
    final String title = "Okhttp3 예제";

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = NetworkOkhttp3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        //Internet 연결 체크
        NetworkUtil.getConnectivityStatus(this);

        binding.button.setOnClickListener(v -> {
            loadDataA();
        });

        binding.button2.setOnClickListener(v -> {
            loadDataB();
        });
    }

    // Okhttp3와 Thread를 사용해서 JSON 자료 불러오기. (url : https://jsonplaceholder.typicode.com/posts)
    public void loadDataA() {
        new Thread(() -> {
            try {
                String url = new retrofit_client().getBaseUrl() + "posts";
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder().url(url).get();

                Request request = builder.build();

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String str = dealJsonA(body.string());
                        handler.post(() -> binding.textView1.setText(str + "\n"));
                    }
                } else {
                    System.err.println("Error Occurred");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 받아온 JSON을 JSONArray와 JSONObject를 이용해서 처리
    public String dealJsonA(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("json", jsonObject.toString());

                String id = jsonObject.getString("id");
                String title = jsonObject.getString("title");
                sb.append("id : " + id + "\n");
                sb.append("title : " + title + "\n\n");
            };

            return sb.toString();

        } catch (JSONException err) {
            Log.d("Error", err.toString());
            return "There is a problem";
        }
    }

    // Okhttp3와 Callback 사용해서 JSON 자료 불러오기. (url : https://jsonplaceholder.typicode.com/posts)
    public void loadDataB() {

        OkHttpClient okHttpClient = new OkHttpClient();
        String url = new retrofit_client().getBaseUrl() + "posts";

        Request request = new Request.Builder().url(url).build();
        Callback callback = new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    List<data_model> list = dealJsonB(response.body().string());

                    StringBuilder sb = new StringBuilder();

                    for (data_model data : list) {
                        sb.append("id : " + data.getID() + "\n");
                        sb.append("title : " + data.getTitle() + "\n\n");
                    }
                    handler.post(() -> binding.textView1.setText(sb.toString()));
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        };

        okHttpClient.newCall(request).clone().enqueue(callback);

        String responseString;
    }

    // Gson : https://codechacha.com/ko/java-gson/
    // Gson을 이용해 JSON을 한번에 다 객체로 바꿈
    public List<data_model> dealJsonB(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<data_model>>() {}.getType();
        List<data_model> list = gson.fromJson(json, listType);
        return list;
    }

}