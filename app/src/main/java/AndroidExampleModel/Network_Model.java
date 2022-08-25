package AndroidExampleModel;

import AndroidBasic.Network.HTTPURLConnections.HttpURLConnections;
import AndroidBasic.Network.Okhttp3.Okhttp3_Activity;
import AndroidBasic.Network.Retrofit2.Retrofit2_Activity;
import AndroidExampleList.Android_Example_Item;
import java.util.Arrays;
import java.util.List;

public class Network_Model {

    //Singleton Pattern 적용------------
    private Network_Model() { }

    private static class SingletonHolder {
        public static final Network_Model INSTANCE = new Network_Model();
    }
    public static Network_Model getInstance() {
        return Network_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    //android_database_list
    //전체적 구성이 RecyclerView의 Item과 똑같아서 그대로 재활용
    private final List<Android_Example_Item> network_list = Arrays.asList(
            new Android_Example_Item(1, "HttpURLConnection", true),
            new Android_Example_Item(2, "OKhttp3", true),
            new Android_Example_Item(3, "Retrofit2", true)
    );

    public List<Android_Example_Item> get_network_list() {
        return this.network_list;
    }


    //안드로이드 데이터베이스 class List
    private final List<Class> network_class_list = Arrays.asList(
            HttpURLConnections.class,        //1
            Okhttp3_Activity.class,    //2
            Retrofit2_Activity.class
    );

    public List<Class> get_network_class_list() {
        return this.network_class_list;
    }


    private final String url = "https://gogiyum.com/api/tag";
}
