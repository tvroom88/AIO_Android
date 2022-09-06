package AndroidBasic.ImageLibraries.LoadImage;

import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aio_android.R;
import com.example.aio_android.databinding.ImageLibrariesBinding;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * 이미지 library들을 이용에 url과 연결시킨 이미지
 */
public class ImageLibrariesActivity extends BaseActivity {

    private ImageLibrariesBinding mBinding;
    final String TITLE = "이미지 로드 라이브러리들";

    // 무료 placeholder 이미지 URL (API) 사이트 : https://picsum.photos/
    private final String imageURL = "https://picsum.photos/600/600";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);


        mBinding = ImageLibrariesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        //Glide를 이용해 이미지 불러오기
        loadImageWithGlide(imageURL);

        //Picasso를 이용해 이미지 불러오기
        loadImageWithPicasso(imageURL);

        //Fresco를 이용해 이미지 불러오기
        loadImageWithFresco(imageURL);
    }

    //Glide를 이용해 이미지 불러오기
    private void loadImageWithGlide(String imageURL) {
        Glide.with(this)
                .load(imageURL)
                .override(600, 600)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(mBinding.imageLibraryImageview1);
    }

    //Picasso를 이용해 이미지 불러오기
    private void loadImageWithPicasso(String imageURL) {
        // Display a jpg image from the given url
//        Picasso.
        Picasso.get()
                .load(imageURL)
                .into(mBinding.imageLibraryImageview2);
    }

    //Fresco를 이용해 이미지 불러오기
    private void loadImageWithFresco(String imageURL) {
//        Uri uri = Uri.parse("http://frescolib.org/static/fresco-logo.png");
//        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        mBinding.simpleDraweeView.setImageURI(imageURL);
    }
}