package AndroidBasic.AndroidFourComponents.BottomNavigationFragmentView;

import AndroidBasic.AndroidFourComponents.ActivityAndFragment.FragmentA;
import AndroidBasic.AndroidFourComponents.ActivityAndFragment.FragmentB;
import AndroidBasic.RecyclerAndListView.ListViewFragment;
import AndroidBasic.RecyclerAndListView.RecyclerViewFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.BottomNavigationFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * BottomNavigation 예제
 * ActivityAndFragment directory에 있는 FragmentA와 FragmentB 재사용
 */
public class BottomNavigationFragmentActivity extends BaseActivity {

    private BottomNavigationFragmentBinding binding;

    FragmentA fragment1;
    FragmentB fragment2;

    final String titleA =  "Fragment A";
    final String titleB =  "Fragment B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_fragment);

        binding = BottomNavigationFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleA);

        fragment1 = new FragmentA("fragment A");
        fragment2 = new FragmentB("fragment B");

        getSupportFragmentManager().beginTransaction().replace(R.id.bottomnavigation_container, fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.div_tab1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomnavigation_container, fragment1).commit();
                    setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleA);

                    return true;
                case R.id.div_tab2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomnavigation_container, fragment2).commit();
                    setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, titleB);
                    return true;
            }
            return false;
        });

    }
}