package AndroidBasic.AndroidFourComponents.ActivityAndFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.aio_android.R;
import com.example.aio_android.databinding.FragmentABinding;
import com.example.aio_android.databinding.FragmentBBinding;

public class FragmentB extends Fragment {

    private FragmentBBinding binding;
    String text;

    public FragmentB(){

    }
    public FragmentB(String text){
       this.text = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fragmentBText.setText(text);
        return root;
    }
}