package AndroidExampleList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.R;

import java.util.List;

public class Android_Example_List_Adapter extends RecyclerView.Adapter<Android_Example_List_Adapter.CustomViewHolder> {

    private List<Android_Example_Item> android_example_code_list = null;

    //생성자로 Model에서 받아올 내용 초기화.
    public Android_Example_List_Adapter(List<Android_Example_Item> data){
        this.android_example_code_list = data;
    }


    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.android_example_list_item, parent, false);
        Android_Example_List_Adapter.CustomViewHolder viewholder = new Android_Example_List_Adapter.CustomViewHolder(view);

        return viewholder;

    }

    //여기서 View와 받아온 데이터를 연결시켜주는 부분.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Android_Example_Item item = android_example_code_list.get(position);

        holder.number.setText(String.valueOf(item.getNumber()));
        holder.title.setText(item.getTitle());
        boolean isContent = item.isContent();

        if (isContent) {
            holder.image1.setImageResource(R.drawable.android_example_right);
        } else {
            holder.image1.setImageResource(R.drawable.android_example_point_three);
        }

    }

    @Override
    public int getItemCount() {
        return android_example_code_list.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스 - 여기서 뷰 객체를 참조해주는듯.
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        TextView title;
        ImageView image1;


        public CustomViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조
            number = itemView.findViewById(R.id.android_example_number);
            title = itemView.findViewById(R.id.android_example_title);
            image1 = itemView.findViewById(R.id.android_example_image);
        }
    }
}
