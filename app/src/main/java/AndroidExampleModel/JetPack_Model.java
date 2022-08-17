package AndroidExampleModel;

import AndroidBasic.JetPack.DataAndViewBinding;
import AndroidExampleList.Android_Example_Item;
import java.util.Arrays;
import java.util.List;

public class JetPack_Model {


    //Singleton Pattern 적용------------
    private JetPack_Model() { }

    private static class SingletonHolder {
        public static final JetPack_Model INSTANCE = new JetPack_Model();
    }
    public static JetPack_Model getInstance() {
        return JetPack_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    private final List<Android_Example_Item> JecPack_List = Arrays.asList(
            new Android_Example_Item(1, "DataBinding과 ViewBinding", true),
            new Android_Example_Item(2, "DataBinding과 ViewBinding", true)
    );


    public List<Android_Example_Item> getJecPack_List() {
        return this.JecPack_List;
    }

    //JetPack class List
    private final List<Class> jetpack_class_list = Arrays.asList(
            DataAndViewBinding.class, //1
            DataAndViewBinding.class //1
    );

    //Getter method for JetPack class List
    public List<Class> get_JetPack_Class_List() {
        return this.jetpack_class_list;
    }

}
