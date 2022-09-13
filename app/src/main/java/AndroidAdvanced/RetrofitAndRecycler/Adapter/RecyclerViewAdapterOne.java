package AndroidAdvanced.RetrofitAndRecycler.Adapter;

import AndroidAdvanced.RetrofitAndRecycler.DB.Picsum_Model;
import AndroidBasic.RecyclerAndListView.RecyclerViewAdapter;
import AndroidBasic.RecyclerAndListView.Student;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.aio_android.R;
import com.example.aio_android.databinding.RecyclerAndListItemBinding;
import com.example.aio_android.databinding.RetrofitAndRecyclerItemBinding;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewAdapterOne  extends RecyclerView.Adapter<RecyclerViewAdapterOne.CustomViewHolder> {

    private final List<Picsum_Model> android_example_code_list;

    private RetrofitAndRecyclerItemBinding binding;

    //imageView1 ,author, width, height, url

    public RecyclerViewAdapterOne(List<Picsum_Model> android_example_code_list) {
        this.android_example_code_list = android_example_code_list;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewAdapterOne.CustomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        binding = RetrofitAndRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false);
        View view = binding.getRoot();
        RecyclerViewAdapterOne.CustomViewHolder viewholder = new RecyclerViewAdapterOne.CustomViewHolder(view, parent.getContext());

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapterOne.CustomViewHolder holder, int position) {

        Picsum_Model item = android_example_code_list.get(position);

        //Image 부분 url로 연결시키기 (Glide 라이브러리 사용)
        Glide.with(holder.image.getContext())
                .load(item.getDownload_url())
                .override(100, 100)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(holder.image);

        // Text 부분
        holder.id.setText(item.getId());
        holder.author.setText(item.getAuthor());
        holder.width.setText(item.getWidth());
        holder.height.setText(item.getHeight());
        holder.url.setText(item.getDownload_url());

    }

    @Override
    public int getItemCount() {
        return android_example_code_list.size();
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스 - 여기서 뷰 객체를 참조해주는듯.
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView id;
        TextView author;
        TextView width;
        TextView height;
        TextView url;

        public CustomViewHolder(@NonNull @NotNull View itemView, Context mContext) {
            super(itemView);
            image = binding.imageView1;
            id = binding.id;
            author = binding.author;
            width = binding.width;
            height = binding.height;
            url = binding.url;
        }
    }
}
