package AndroidBasic.AndroidFourComponents.ActivityAndFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.aio_android.databinding.FragmentABinding;

public class FragmentA extends Fragment {

    private FragmentABinding binding;
    String text;

    public FragmentA(){

    }
    public FragmentA(String text){
        this.text = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fragmentAText.setText(text);

        return root;

    }
}