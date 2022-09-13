package AndroidExampleModel;

import AndroidAdvanced.RetrofitAndRecycler.RetrofitAndRecyclerActivity;
import AndroidBasic.AndroidDesign.AndroidDesign_List;
import AndroidBasic.AndroidFourComponents.Four_Components_List;
import AndroidBasic.AndroidLifeCycle.LifeCycleActivity;
import AndroidBasic.DataBase.DataBase_List;
import AndroidBasic.DiverseLayout.DiverseLayoutActivity;
import AndroidBasic.GridView.GridViewActivity;
import AndroidBasic.Handler.HandlerActivity;
import AndroidBasic.ImageLibraries.ImageLibraries_List;
import AndroidBasic.IntentAndBundle.IntentAndBundleActivity;
import AndroidBasic.JetPack.Jetpack_List;
import AndroidBasic.JsonData.JsonData_Activity;
import AndroidBasic.Network.Network_List;
import AndroidBasic.RecyclerAndListView.RecyclerAndListActivity;
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

    // AndroidBasic 리스트
    private final List<Android_Example_Item> android_example_code_list = Arrays.asList(
            new Android_Example_Item(1, "안드로이드 4대 컴포넌트", false),
            new Android_Example_Item(2, "안드로이드, 프래그먼트 라이프 사이클", true),
            new Android_Example_Item(3, "인텐트와 번들", true),
            new Android_Example_Item(4, "핸들러", true),
            new Android_Example_Item(5, "다양한 레이아웃", true),
            new Android_Example_Item(6, "데이터베이스", false),
            new Android_Example_Item(7, "Json 데이터 처리", true),
            new Android_Example_Item(8, "네트워트 통신", false),
            new Android_Example_Item(9, "리스트뷰, 리사이클러뷰", true),
            new Android_Example_Item(10, "그리드뷰", true),
            new Android_Example_Item(11, "디자인패턴", false),
            new Android_Example_Item(12, "이미지 라이브러리", false),
            new Android_Example_Item(13, "JetPack", false)

//            new Android_Example_Item(9, "멀티미디어 다루기", false),
//            new Android_Example_Item(9, "외부 데이터베이스와 연동", false),
//            new Android_Example_Item(9, "위치 기반 서비스", false),
//            new Android_Example_Item(9, "푸시와 센서 기능 이용하기", false)
    );

    public List<Android_Example_Item> getAndroid_example_code_list() {
        return this.android_example_code_list;
    }

    // AndroidBasic class 리스트
    private final List<Class> class_list = Arrays.asList(
            Four_Components_List.class, //1
            LifeCycleActivity .class, //2
            IntentAndBundleActivity.class, //3
            HandlerActivity.class, //4
            DiverseLayoutActivity.class, //5
            DataBase_List.class, //6
            JsonData_Activity.class, //7
            Network_List.class,  //8
            RecyclerAndListActivity.class, //9
            GridViewActivity.class, // 10
            AndroidDesign_List.class, //11
            ImageLibraries_List.class, //12
            Jetpack_List.class //12

    );

    public List<Class> get_Class_List() {
        return this.class_list;
    }




    //---------------------------------------------------------------
    // AndroidAdvanced 리스트
    private final List<Android_Example_Item> android_advanced_code_list = Arrays.asList(
            new Android_Example_Item(1, "Retrofit + Room + Recycler", false)
    );

    public List<Android_Example_Item> getAndroid_advanced_code_list() {
        return this.android_advanced_code_list;
    }

    // AndroidBasic class 리스트
    private final List<Class> android_advanced_class_list = Arrays.asList(
            RetrofitAndRecyclerActivity.class //1
    );

    public List<Class> get_Android_Advanced_Class_List() {
        return this.android_advanced_class_list;
    }
}
