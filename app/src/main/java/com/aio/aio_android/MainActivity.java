package com.aio.aio_android;

import AndroidExampleList.Android_Example_List;
import AndroidExampleModel.MainActivity_Model;
import android.content.Intent;
import android.graphics.Insets;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import com.example.aio_android.BuildConfig;
import com.example.aio_android.R;
import com.example.aio_android.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

// 앱의 가장 첫번째 화면 - 이 화면만 NavigationView가 추가되어 있습니다.
// MainActivity_Model version 정보와 메뉴 클릭시 나오는 dialog textview들의 내용 넣어놓음.
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    final String title = "안드로이드 예제 앱";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);
        binding.layout.toolbarImage.setImageResource(R.drawable.three_line);


        binding.layout.toolbarImage.setOnClickListener(v -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });

        // 첫화면과 안드로이드 예제 리스트와 연결
        binding.MainActivityButton1.setOnClickListener(v -> {
            setOnClickBasicButton();
        });

        binding.MainActivityButton2.setOnClickListener(v -> {
            setOnClickAdvancedButton();
        });

        binding.navigationView.setNavigationItemSelectedListener(this);

        // 버전정보 세팅하기 - 정보는
        View nav_header_view = binding.navigationView.getHeaderView(0);
        TextView nav_header_id_text = nav_header_view.findViewById(R.id.version);
        nav_header_id_text.setText("Version : " + (double) BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.div_tab1:
                setOnClickBasicButton();
                break;
            case R.id.div_tab2:
                setOnClickAdvancedButton();
                break;
            case R.id.div_tab3:
                setOnClickHelpMenu();
                break;
            case R.id.div_tab4:
                setOnClickInformationMenu();
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() { //뒤로가기 했을 때
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 기초 버튼 눌렀을때
    public void setOnClickBasicButton(){
        Intent intent = new Intent(this, Android_Example_List.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    // 심화 버튼 눌렀을떄
    public void setOnClickAdvancedButton(){
        Toast myToast = Toast.makeText(this.getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT);
        myToast.show();
    }

    // 왼쪽 상단 메뉴 부분에 있는 information 버튼 클릭시 Custom Dialog가 나오는 부분
    public void setOnClickHelpMenu(){
        CustomDialog dialog = new CustomDialog(this);
        makeDialog(dialog);
        dialog.setTitle("[ 도움말 ]");
        dialog.addTexts(MainActivity_Model.getInstance().getContentList());
    }

    // 왼쪽 상단 메뉴 부분에 있는 information 버튼 클릭시 Custom Dialog가 나오는 부분
    public void setOnClickInformationMenu(){
        CustomDialog dialog = new CustomDialog(this);
        makeDialog(dialog);
        dialog.setTitle("[ 정보 ]");
        dialog.addTexts(MainActivity_Model.getInstance().getInfoContentsList());

        //링크가 있는 TextView는 직접 추가해서 넣어주게 코들를 만듬
        Button link = new Button(this);
        link.setText("블로그 자료로 이동");
        link.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://growing-software-engineer.tistory.com/category/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%20%EA%B8%B0%EC%B4%88%20%EC%A7%80%EC%8B%9D"));
            startActivity(intent);
        });
        dialog.addTextView(link);
    }


    public void makeDialog(CustomDialog dialog){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

        // Custon Dialog 크기 설정. Width 는
        Window window = dialog.getWindow();
        int width = (int) (getWidthSize() * 0.8);
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setLayout(width, height);
    }

    // Custom Dialog의 크기를 실제 폰 가로 사이즈에 따라 바꾸게 하기위해 설정
    public int getWidthSize(){
        int width = 0;
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrices = wm.getCurrentWindowMetrics();
            Insets insets = windowMetrices.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            width = windowMetrices.getBounds().width() - insets.left - insets.right;
        }else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            width = displayMetrics.widthPixels;
        }
        return width;
    }

    // 여기서 사용하지는 않지만 나중에 필요할때 사용하려고 여기 넣어둠
    public int getHeightSize(){
        int height = 0;
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrices = wm.getCurrentWindowMetrics();
            Insets insets = windowMetrices.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            height = windowMetrices.getBounds().height() - insets.bottom - insets.top;
        }else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = displayMetrics.heightPixels;
        }
        return height;
    }
}














