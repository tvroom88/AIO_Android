package AndroidExampleModel;

import AndroidBasic.AndroidFourComponents.ActivityAndFragment.Activity_And_Fragment;
import AndroidBasic.AndroidFourComponents.BottomNavigationFragmentView.BottomNavigationFragmentActivity;
import AndroidBasic.AndroidFourComponents.BroadcastReceiver.BroadcastActivity;
import AndroidBasic.AndroidFourComponents.BroadcastReceiver.Broadcast_SMS_Activity;
import AndroidBasic.AndroidFourComponents.ContentProvider.ContentProviderActivity;
import AndroidBasic.AndroidFourComponents.ContentResolverWithAlbum.ContentResolverWithAlbum;
import AndroidBasic.AndroidFourComponents.ContentResolverWithPhonebook.ContentResolverWIthPhoneBook;
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
    private final List<Android_Example_Item> android_four_component_list = Arrays.asList(
            new Android_Example_Item(1, "Activity and Fragment", true),
            new Android_Example_Item(2, "BottomNavigation and Fragment", true),
            new Android_Example_Item(3, "Service", true),
            new Android_Example_Item(4, "Content provider", true),
            new Android_Example_Item(5, "Content resolver with Album", true),
            new Android_Example_Item(6, "Content resolver with Phone book", true),
            new Android_Example_Item(7, "Broadcast receiver", true)
    );

    public List<Android_Example_Item> getAndroid_four_component_list() {
        return this.android_four_component_list;
    }


    //안드로이드 four component class List
    private final List<Class> four_component_class_list = Arrays.asList(
            Activity_And_Fragment.class,        //1
            BottomNavigationFragmentActivity.class,              //2
            ServiceActivity.class,              //3
            ContentProviderActivity.class,      //4
            ContentResolverWithAlbum.class,     //5
            ContentResolverWIthPhoneBook.class, //6
            BroadcastActivity.class             //7
    );

    //Getter method for 안드로이드 four component class List
    public List<Class> get_Four_Component_Class_List() {
        return this.four_component_class_list;
    }
}
