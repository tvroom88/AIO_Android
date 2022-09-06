package AndroidExampleModel;

import AndroidBasic.AndroidDesign.MVC.MVC_easy_example;
import AndroidBasic.AndroidDesign.MVP.MVP_easy_example;
import AndroidBasic.AndroidDesign.MVVM.MVVM_easy_example;
import AndroidExampleList.Android_Example_Item;

import java.util.Arrays;
import java.util.List;

public class Android_Design_Model {

    //Singleton Pattern 적용------------
    private Android_Design_Model() { }

    private static class SingletonHolder {
        public static final Android_Design_Model INSTANCE = new Android_Design_Model();
    }
    public static Android_Design_Model getInstance() {
        return Android_Design_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    //android_design_list
    //전체적 구성이 RecyclerView의 Item과 똑같아서 그대로 재활용
    private final List<Android_Example_Item> android_design_list = Arrays.asList(
            new Android_Example_Item(1, "MVC", true),
            new Android_Example_Item(2, "MVP", true),
            new Android_Example_Item(3, "MVVM", true)

    );

    public List<Android_Example_Item> get_android_design_list() {
        return this.android_design_list;
    }


    //안드로이드 데이터베이스 class List
    private final List<Class> android_design_class_list = Arrays.asList(
            MVC_easy_example.class,       //1
            MVP_easy_example.class,       //2
            MVVM_easy_example.class       //3
    );

    public List<Class> getAndroid_design_class_list_class_list() {
        return this.android_design_class_list;
    }
}
