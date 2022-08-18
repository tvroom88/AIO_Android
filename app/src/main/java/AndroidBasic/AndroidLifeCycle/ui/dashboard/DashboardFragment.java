package AndroidBasic.AndroidLifeCycle.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.aio_android.databinding.LifecycleFragmentBBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private LifecycleFragmentBBinding binding;

    public static final String TAG = "DashboardFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.i(TAG,"onCreateView");

        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = LifecycleFragmentBBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final TextView textView2 = binding.textDashboard2;
        dashboardViewModel.getText2().observe(getViewLifecycleOwner(), textView2::setText);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // add your code here which executes when fragment's instance initializes
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // View의 초기값 설정, LiveData 옵저빙, RecyclerView, ViewPager2에 사용될 Adapter 세팅은 이 메소드에서 해주는 것이 적절함
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"onViewCreated");
    }

    @Override
    public void onStart() {
        // add your code here which executes when the Fragment gets visible.
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    public void onResume() {
        // add your code here which executes when the Fragment is visible and intractable.
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onPause() {
        // add your code here which executes when user leaving the current fragment or fragment is no longer intractable.
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        // add your code here which executes Fragment going to be stopped.
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
        binding = null;
    }

    @Override
    public void onDestroy() {
        // add your code here which executes when the final clean up for the Fragment's state is needed.
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

}
