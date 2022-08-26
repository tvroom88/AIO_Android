package AndroidBasic.RecyclerAndListView;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.FourComponent_Model;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.aio_android.R;
import com.example.aio_android.databinding.FourComponentsListBinding;
import com.example.aio_android.databinding.FragmentListViewBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListViewFragment extends Fragment {

    private FragmentListViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListViewBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        List<Student> studentList = Add100Student();

        ListViewAdapter adapter = new ListViewAdapter(studentList);

        binding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
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