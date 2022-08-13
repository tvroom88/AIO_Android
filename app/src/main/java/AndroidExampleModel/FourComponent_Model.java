package AndroidExampleModel;

import AndroidBasic.AndroidFourComponents.ActivityAndFragment.Activity_And_Fragment;
import AndroidBasic.AndroidFourComponents.ContentProvider.ContentProviderActivity;
import AndroidBasic.AndroidFourComponents.Service.ServiceActivity;
import AndroidExampleList.Android_Example_Item;
import java.util.Arrays;
import java.util.List;

public class FourComponent_Model {

    //Singleton Pattern 적용------------
    private FourComponent_Model() { }

    private static class SingletonHolder {
        public static final FourComponent_Model INSTANCE = new FourComponent_Model();
    }
    public static FourComponent_Model getInstance() {
        return FourComponent_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

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


    //안드로이드 four component class List
    private List<Class> four_component_class_list = Arrays.asList(
            Activity_And_Fragment.class, //1
            ServiceActivity.class,       //2
            ContentProviderActivity.class, //3
            ServiceActivity.class        //4
    );

    //Getter method for 안드로이드 four component class List
    public List<Class> get_Four_Component_Class_List() {
        return this.four_component_class_list;
    }
}
