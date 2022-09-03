package AndroidBasic.GridView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.aio_android.databinding.GridviewListItemBinding;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private final List<GridViewItem> gridViewItemsList;
    private GridviewListItemBinding mBinding;


    public GridViewAdapter(List<GridViewItem> gridViewItemsList){
        this.gridViewItemsList = gridViewItemsList;

    }

    @Override
    public int getCount() {
        return gridViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return gridViewItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mBinding = GridviewListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        convertView = mBinding.getRoot();

        GridViewItem item = gridViewItemsList.get(position);
        mBinding.tvNum.setText(item.getNum());
        mBinding.tvName.setText(item.getName());
        mBinding.ivIcon.setImageResource(item.getResId()); //이미지 적용

        return convertView;
    }
}
