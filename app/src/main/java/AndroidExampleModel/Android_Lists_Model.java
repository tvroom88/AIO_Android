package AndroidExampleModel;

import AndroidBasic.AndroidFourComponents.Four_Components_List;
import AndroidBasic.JetPack.Jetpack_List;
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
            new Android_Example_Item(2, "안드로이드, 프래그먼트 라이프 사이클", true),
            new Android_Example_Item(3, "인텐트", true),
            new Android_Example_Item(4, "핸들러", true),
            new Android_Example_Item(5, "컨텍스트", true),
            new Android_Example_Item(6, "디자인패턴", true),
            new Android_Example_Item(7, "리스트뷰, 리사이클러뷰", true),
            new Android_Example_Item(8, "JetPack", false)
    );

    public List<Android_Example_Item> getAndroid_example_code_list() {
        return this.android_example_code_list;
    }


    private List<Class> class_list = Arrays.asList(
            Four_Components_List.class, //1
            Four_Components_List.class, //2
            Four_Components_List.class, //3
            Four_Components_List.class, //4
            Four_Components_List.class, //5
            Four_Components_List.class, //6
            Four_Components_List.class, //7
            Jetpack_List.class //8
    );

    public List<Class> get_Class_List() {
        return this.class_list;
    }

    
}
