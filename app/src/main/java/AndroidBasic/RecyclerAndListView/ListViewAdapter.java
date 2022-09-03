package AndroidBasic.RecyclerAndListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.aio_android.R;
import com.example.aio_android.databinding.RecyclerAndListItemBinding;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private final List<Student> studentList;
    private RecyclerAndListItemBinding binding;


    public ListViewAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }


    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        Student item = studentList.get(position);
        binding = RecyclerAndListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        convertView = binding.getRoot();
        binding.recyclerId.setText(String.valueOf(item.getId()));
        binding.recyclerName.setText(item.getName());
        binding.recyclerAge.setText(String.valueOf(item.getAge()));

        return convertView;
    }
}
