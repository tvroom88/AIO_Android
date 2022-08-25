package AndroidBasic.Network.HTTPURLConnections;

import AndroidBasic.Network.NetworkUtil;
import AndroidBasic.Network.Network_List;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.NetworkHttpUrlconnectionBinding;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnections extends BaseActivity {

    private NetworkHttpUrlconnectionBinding binding;
    final String title = "HttpURLConnection 예제";

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_http_urlconnection);

        binding = NetworkHttpUrlconnectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        //Internet 연결 체크
        NetworkUtil.getConnectivityStatus(this);

        binding.button.setOnClickListener(v -> {
            new Thread(() -> {
                StringBuilder data = get("https://www.naver.com" );
                handler.post(() -> binding.textView1.setText(data + "\n"));
            }).start();
        });
    }

    /**
     * HTTP GET request
     * setRequestProperty 참고 문헌 :
     * https://mytalkhome.tistory.com/entry/HttpURLConnection-%EC%82%AC%EC%9A%A9%EC%8B%9C-%EC%B0%B8%EA%B3%A0%EC%82%AC%ED%95%AD
     */
    public StringBuilder get(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // add request header

            if (conn != null) {
                conn.setConnectTimeout(10000); //서버 접속
                conn.setReadTimeout(10000);    //Read시

                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                output.append("Http 응답 code : " + resCode + "\n" + "Http body");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception ex) {
            output.append("예외 발생함");
        }
        return output;
    }


}