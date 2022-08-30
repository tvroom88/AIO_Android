package AndroidBasic.AndroidLifeCycle;

import android.os.Bundle;
import android.util.Log;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.LifeCycleBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Activity와 fragment의 생명주기 예제
 * (1) Log를 이용해 각 생명주기 부분에서 잘되는지 확인
 * (2) 특별히 뷰로 보여줄게 없어서 스크린샷등으로 대체
 */
//public class LifeCycleActivity extends AppCompatActivity {
public class LifeCycleActivity extends BaseActivity {

    private LifeCycleBinding binding;

    //Activity 이름 가져오는 부분
    public static final String TAG = "LifeCycleActivity";

    final String title = "라이프 사이클 예제";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");

        binding = LifeCycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar 세팅
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_life_cycle);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        super.onStart();

    }

    @Override
    protected void onStart() {
        //액티비티가 화면에 나타나기 시작
        super.onStart();
        Log.i(TAG,"onStart");
    }
    @Override
    protected void onRestart() {
        //액티비티가 중단되었다가 다시 시작
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        //액티비티가 화면에 나타나고 상호작용이 가능해짐
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        //다른 액티비티가 시작되려함, 이 액티비티는 중단되려하고 백그라운드로 들어갑니다.
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        //액티비티가 더 이상 화면에 나타나지 않음,중단된 상태
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        //액티비티가 종료되려고 합니다.
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}