package AndroidExampleData;

import AndroidBasic.AndroidFourComponents.Android_Four_Components_List;
import AndroidExampleList.Android_Example_Item;

import java.util.Arrays;
import java.util.List;

// 안드로이드 예제 List에 나오는 모든 데이터(번호, 타이틀, 아이콘) 정보들을 여기에 저장해줄 예정. 아래는 model을 사용한 Activity List.
// 자주 사용됨으로 쉽게 접근하기 위해 Singleton Pattern 적용.
// (1) Android_Example_List (AndroidExampleList package)
// (2) AndroidBasic package
public class Android_Lists_Model {

    //Singleton Pattern 적용------------
    private Android_Lists_Model() { }

    private static class SingletonHolder {
        public static final Android_Lists_Model INSTANCE = new Android_Lists_Model();
    }
    public static Android_Lists_Model getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    // RecyclerView의 Item에 들어갈 내용들
    private List<Android_Example_Item> android_example_code_list = Arrays.asList(
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

    public List<Android_Example_Item> getAndroid_example_code_list() {
        return this.android_example_code_list;
    }


    private List<Class> class_list = Arrays.asList(
            Android_Four_Components_List.class,
            Android_Four_Components_List.class,
            Android_Four_Components_List.class,
            Android_Four_Components_List.class,
            Android_Four_Components_List.class,
            null,
            null,
            null,
            null,
            null,
            null
    );

    public List<Class> get_Class_List() {
        return this.class_list;
    }


    //Android_Four_Component_List
    //전체적 구성이 RecyclerView의 Item과 똑같아서 그대로 재활용
    private List<Android_Example_Item> android_four_component_list = Arrays.asList(
            new Android_Example_Item(1, "Activity and Fragment", true),
            new Android_Example_Item(2, "Service", true),
            new Android_Example_Item(3, "Contents provider", true),
            new Android_Example_Item(4, "Broadcast receiver", true)
    );


    public List<Android_Example_Item> getAndroid_four_component_list() {
        return this.android_four_component_list;
    }

}
