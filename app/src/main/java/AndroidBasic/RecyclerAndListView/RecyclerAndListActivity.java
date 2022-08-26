package AndroidBasic.RecyclerAndListView;

import AndroidBasic.DiverseLayout.LayoutFragmentA;
import AndroidBasic.DiverseLayout.LayoutFragmentB;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.RecyclerAndListBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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
