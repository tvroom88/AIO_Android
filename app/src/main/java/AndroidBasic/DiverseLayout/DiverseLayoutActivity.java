package AndroidBasic.DiverseLayout;

import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.DiverseLayoutBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 다양한 Layout들이 추가된 예제
 * 1. Linearlayout, ConstraintLayout, TableLayout, FrameLayout 예제 추가
 * 2. 1번의 예제를 xml과 동적(java)로 나눠서 구현.
 */
public class DiverseLayoutActivity extends BaseActivity {

    private DiverseLayoutBinding binding;
    String titleA = "다양한 Layout들 - xml로 구현";
    String titleB = "다양한 Layout들 - 동적으로 구현";

    LayoutFragmentA fragment1;
    LayoutFragmentB fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diverse_layout);

        binding = DiverseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // fragment불로엄
        fragment1 = new LayoutFragmentA();
        fragment2 = new LayoutFragmentB();
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleA);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {
                case R.id.div_tab1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment1).commit();

                    setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleA);

                    return true;
                case R.id.div_tab2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment2).commit();

                    setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleB);
                    return true;
            }
            return false;
        });
    }
}