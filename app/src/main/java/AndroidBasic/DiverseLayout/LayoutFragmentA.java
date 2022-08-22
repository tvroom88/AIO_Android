package AndroidBasic.DiverseLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.aio_android.databinding.DivlayoutFragmentABinding;

public class LayoutFragmentA extends Fragment {

    private DivlayoutFragmentABinding binding;
    int imageIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DivlayoutFragmentABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.button7.setOnClickListener(v -> {
            changeImage();
        });

        return root;
    }

    // 이미지 바꿔주는 메소드
    private void changeImage() {
        imageIndex++;
        if(imageIndex > 2){
            imageIndex = 0;
        }
        if (imageIndex == 0) {
            binding.imageView.setVisibility(View.VISIBLE);
            binding.imageView2.setVisibility(View.INVISIBLE);
            binding.imageView3.setVisibility(View.INVISIBLE);
        } else if (imageIndex == 1) {
            binding.imageView.setVisibility(View.INVISIBLE);
            binding.imageView2.setVisibility(View.VISIBLE);
            binding.imageView3.setVisibility(View.INVISIBLE);
        } else if (imageIndex == 2){
            binding.imageView.setVisibility(View.INVISIBLE);
            binding.imageView2.setVisibility(View.INVISIBLE);
            binding.imageView3.setVisibility(View.VISIBLE);
        }
    }
}