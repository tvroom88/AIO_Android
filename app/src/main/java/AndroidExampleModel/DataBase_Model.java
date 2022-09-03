package AndroidExampleModel;

import AndroidBasic.DataBase.Realm.RealmActivity;
import AndroidBasic.DataBase.RoomDataBase.RoomDBActivity;
import AndroidBasic.DataBase.SQLiteDataBase.SQLiteActivity;
import AndroidExampleList.Android_Example_Item;

import java.util.Arrays;
import java.util.List;

public class DataBase_Model {

    //Singleton Pattern 적용------------
    private DataBase_Model() { }

    private static class SingletonHolder {
        public static final DataBase_Model INSTANCE = new DataBase_Model();
    }
    public static DataBase_Model getInstance() {
        return DataBase_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    //android_database_list
    //전체적 구성이 RecyclerView의 Item과 똑같아서 그대로 재활용
    private final List<Android_Example_Item> database_list = Arrays.asList(
            new Android_Example_Item(1, "SQLite", true),
            new Android_Example_Item(2, "Room 라이브러리", true),
            new Android_Example_Item(3, "Realm 라이브러리", true)

    );

    public List<Android_Example_Item> get_database_list() {
        return this.database_list;
    }


    //안드로이드 데이터베이스 class List
    private final List<Class> database_class_list = Arrays.asList(
            SQLiteActivity.class,       //1
            RoomDBActivity.class,       //2
            RealmActivity.class        //3
    );

    public List<Class> get_database_class_list() {
        return this.database_class_list;
    }

}

