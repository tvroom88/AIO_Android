package AndroidExampleList;

import java.util.Arrays;
import java.util.List;

public class Android_Example_List_Model {

    // RecyclerView의 Item에 들어갈 내용들
    private final List<Android_Example_Item> android_example_code_list = Arrays.asList(
            new Android_Example_Item(1, "안드로이드 4대 컴포넌트", false),
            new Android_Example_Item(2, "aaaaaaaaa", true),
            new Android_Example_Item(3, "bbbbbbbbb", true),
            new Android_Example_Item(4, "ccccccccc", true),
            new Android_Example_Item(5, "ddddddddd", true),
            new Android_Example_Item(6, "ddddddddd", true),
            new Android_Example_Item(7, "ddddddddd", true),
            new Android_Example_Item(8, "ddddddddd", true),
            new Android_Example_Item(9, "ddddddddd", true),
            new Android_Example_Item(10, "ddddddddd", true),
            new Android_Example_Item(11, "ddddddddd", true)
    );

    public List<Android_Example_Item> getData(){
        return this.android_example_code_list;
    }

}
