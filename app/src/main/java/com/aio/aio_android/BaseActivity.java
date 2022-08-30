package com.aio.aio_android;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import com.example.aio_android.R;

import java.util.Objects;


/**
 *   BaseActivity : 각 Activity가 공통된 포함되는것들 포함.
 *   포함되어 있는것들 (1) Toolbar  (2) Menu 선택하는 부분
 */
abstract public class BaseActivity extends AppCompatActivity {

    protected void setToolbar(Toolbar toolbar, ImageView imageView, TextView titleView, String title){
        // Toolbar 세팅
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // 뒤로가기 버튼
//        imageView.setOnClickListener(v -> finish());
        imageView.setOnClickListener(v -> onBackPressed());

        // Title 제목 세팅
        titleView.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
         return true;
    }
 
    //앱바 메뉴 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu1:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                ActivityCompat.finishAffinity(this);
                overridePendingTransition(0, 0);
                return true;

            case R.id.action_menu2:
                ActivityCompat.finishAffinity(this);
                return true;

            default:
                // 여기에 도달한거는 유저의 action이 아무것도 없을떄 오는 부분
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isFinishing()) {
            // back 버튼으로 화면 종료가 야기되면 동작한다.
            overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        }
    }
}
