package AndroidExampleModel;

import AndroidBasic.GridView.GridViewItem;
import AndroidExampleList.Android_Example_Item;
import com.example.aio_android.R;

import java.util.Arrays;
import java.util.List;

public class GridView_Model {
    //Singleton Pattern 적용------------
    private GridView_Model() { }

    private static class SingletonHolder {
        public static final GridView_Model INSTANCE = new GridView_Model();
    }

    public static GridView_Model getInstance() {
        return GridView_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    //여태까지 사용했던 Icon들 위주로 추가
    private final List<GridViewItem> gridViewItemsList = Arrays.asList(
            new GridViewItem("1", "병아리", R.drawable.chick),
            new GridViewItem("2", "닭", R.drawable.chicken),
            new GridViewItem("3", "책", R.drawable.book),
            new GridViewItem("4", "재활용", R.drawable.recycler),
            new GridViewItem("5", "java 파일", R.drawable.java1),
            new GridViewItem("6", "xml 파일", R.drawable.xml),
            new GridViewItem("7", "A", R.drawable.aa_icon),
            new GridViewItem("8", "B", R.drawable.ab_icon)

    );

    public List<GridViewItem> getGridViewItemsList() {
        return this.gridViewItemsList;
    }
}
