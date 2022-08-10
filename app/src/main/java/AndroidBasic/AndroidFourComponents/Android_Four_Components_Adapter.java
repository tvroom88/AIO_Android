package AndroidBasic.AndroidFourComponents;

import AndroidExampleData.Android_Lists_Model;
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

import java.util.ArrayList;
import java.util.List;

public class Android_Four_Components_Adapter extends BaseAdapter {

    private TextView numTextView;
    private TextView titleTextView;
    private ImageView iconImageView;

    //ListView에
    private List<Android_Example_Item> four_component_list = Android_Lists_Model.getInstance().getAndroid_four_component_list();

    @Override
    public int getCount() {
        return four_component_list.size();
    }

    @Override
    public Object getItem(int position) {
        return four_component_list.get(position);
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
            convertView = inflater.inflate(R.layout.android_example_list_item, parent, false);
        }

        numTextView = (TextView) convertView.findViewById(R.id.android_example_number);
        titleTextView = (TextView) convertView.findViewById(R.id.android_example_title);
        iconImageView = (ImageView) convertView.findViewById(R.id.android_example_image);

        Android_Example_Item item = four_component_list.get(position);

        numTextView.setText(String.valueOf(item.getNumber()));
        titleTextView.setText(item.getTitle());
        boolean isContent = item.isContent();

        if (isContent) {
            iconImageView.setImageResource(R.drawable.android_example_right);
        } else {
            iconImageView.setImageResource(R.drawable.android_example_point_three);
        }


        convertView.setOnClickListener(v -> {

            if(position == 0){
                Intent intent = new Intent(context, Activity_And_Fragment.class);
                context.startActivity(intent);
            }else if(position == 1){

            }else if(position == 2){

            }else{

            }

        });
        return convertView;
    }
}
