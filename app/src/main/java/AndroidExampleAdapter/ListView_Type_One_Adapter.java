package AndroidExampleAdapter;

import AndroidExampleList.Android_Example_Item;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aio_android.R;


import java.util.List;

public class ListView_Type_One_Adapter extends BaseAdapter {

    private final List<Android_Example_Item> android_example_code_list;
    private final List<Class> android_example_class_list;
    private TextView numTextView;
    private TextView titleTextView;
    private ImageView iconImageView;

    public ListView_Type_One_Adapter(List<Android_Example_Item> android_example_code_list, List<Class> android_example_class_list){
        this.android_example_code_list = android_example_code_list;
        this.android_example_class_list = android_example_class_list;
    }

    @Override
    public int getCount() {
        return android_example_code_list.size();
    }

    @Override
    public Object getItem(int position) {
        return android_example_code_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //ListView 내에서 각 position별 위치.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.example_list_item, parent, false);
        }

        numTextView = convertView.findViewById(R.id.android_example_number);
        titleTextView = convertView.findViewById(R.id.android_example_title);
        iconImageView = convertView.findViewById(R.id.android_example_image);

        Android_Example_Item item = android_example_code_list.get(position);

        numTextView.setText(String.valueOf(item.getNumber()));
        titleTextView.setText(item.getTitle());
        boolean isContent = item.isContent();

        if (isContent) {
            iconImageView.setImageResource(R.drawable.android_example_right);
        } else {
            iconImageView.setImageResource(R.drawable.android_example_point_three);
        }

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, android_example_class_list.get(position));
            context.startActivity(intent);
        });
        return convertView;
    }

}
