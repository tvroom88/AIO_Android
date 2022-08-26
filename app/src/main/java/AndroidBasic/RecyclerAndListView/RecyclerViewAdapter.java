package AndroidBasic.RecyclerAndListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.databinding.RecyclerAndListItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private final List<Student> studentList;
    private RecyclerAndListItemBinding binding;

    public RecyclerViewAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        binding = RecyclerAndListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        View view = binding.getRoot();
        RecyclerViewAdapter.CustomViewHolder viewholder = new RecyclerViewAdapter.CustomViewHolder(view, parent.getContext());

        return viewholder;
    }

    //여기서 View와 받아온 데이터를 연결시켜주는 부분.
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CustomViewHolder holder, int position) {
        Student item = studentList.get(position);

        holder.id.setText(String.valueOf(item.getId()));
        holder.name.setText(item.getName());
        holder.age.setText(String.valueOf(item.getAge()));

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스 - 여기서 뷰 객체를 참조해주는듯.
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        TextView age;
        public CustomViewHolder(View view, Context mContext) {
            super(view);

            // 뷰 객체에 대한 참조

            id = binding.recyclerId;
            name = binding.recyclerName;
            age = binding.recyclerAge;

        }
    }
}
