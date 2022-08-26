package AndroidBasic.RecyclerAndListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.databinding.FragmentRecyclerViewBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewFragment extends Fragment {

    RecyclerView mRecyclerView = null;
    RecyclerViewAdapter mAdapter = null;
    private FragmentRecyclerViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerView = binding.recyclerView;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        List<Student> studentList = Add100Student();
        mAdapter = new RecyclerViewAdapter(studentList); // 100개의 객체를 추가시킨 리스트를 넘겨줍니다.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mAdapter.notifyDataSetChanged();


        return root;
    }

    private List<Student> Add100Student(){
        List<Student> list = new ArrayList<>();
        Random rand = new Random(); //instance of random class
        for(int i=1; i<=1000; i++){
            //generate random values from 0-100
            int age = rand.nextInt(60);
            list.add(new Student(i,"name" + i, age));
        }
        return list;
    }
}