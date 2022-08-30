package AndroidBasic.RecyclerAndListView;

import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.RecyclerAndListBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * RecyclerView and ListView 예제.
 * (1) RecyclerView 와 1000 Student 객체 추가
 * (2) ListView 와 1000 Student 객체 추가
 * RecyclerView와 ListView의 성능 차이 확인 가능
 */
public class RecyclerAndListActivity extends BaseActivity {

    private RecyclerAndListBinding binding;
    final String titleA =  "RecyclerView 예제";
    final String titleB =  "ListView 예제";

    RecyclerViewFragment fragment1;
    ListViewFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = RecyclerAndListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment1 = new RecyclerViewFragment();
        fragment2 = new ListViewFragment();
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleA);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
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
