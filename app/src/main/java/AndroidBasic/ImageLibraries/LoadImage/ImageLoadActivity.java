package AndroidBasic.ImageLibraries.LoadImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.ImageLoadBinding;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * 이미지 라이브러리 없이 URL을 이용해 불러온 예제
 */
public class ImageLoadActivity extends BaseActivity {

    private ImageLoadBinding mBinding;
    final String TITLE = "이미지 로드";

    private Bitmap bitmap;

    Handler handler = new Handler(Looper.getMainLooper());

    // 무료 placeholder 이미지 URL (API) 사이트 : https://picsum.photos/
    private final String imageURL = "https://picsum.photos/600/600";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_load);

        mBinding = ImageLoadBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        mBinding.imageLoadBtn.setOnClickListener(v -> {
            loadImage(imageURL);
        });

    }

    private void loadImage(String imageURL) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(imageURL).build();
        Callback callback = new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (response.isSuccessful()) {
                    InputStream is = response.body().byteStream();
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
                    handler.post(() -> mBinding.imageView.setImageBitmap(bitmap));
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        };
        okHttpClient.newCall(request).clone().enqueue(callback);
    }

}

