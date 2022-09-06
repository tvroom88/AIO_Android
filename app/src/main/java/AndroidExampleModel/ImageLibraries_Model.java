package AndroidExampleModel;

import AndroidBasic.AndroidDesign.MVVM.MVVM_easy_example;
import AndroidBasic.ImageLibraries.LoadImage.ImageLibrariesActivity;
import AndroidBasic.ImageLibraries.LoadImage.ImageLoadActivity;
import AndroidExampleList.Android_Example_Item;

import java.util.Arrays;
import java.util.List;

public class ImageLibraries_Model {
    //Singleton Pattern 적용------------
    private ImageLibraries_Model() { }

    private static class SingletonHolder {
        public static final ImageLibraries_Model INSTANCE = new ImageLibraries_Model();
    }
    public static ImageLibraries_Model getInstance() {
        return ImageLibraries_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    //android_design_list
    //전체적 구성이 RecyclerView의 Item과 똑같아서 그대로 재활용
    private final List<Android_Example_Item> image_libraries_list = Arrays.asList(
            new Android_Example_Item(1, "라이브러리 없이 이미지 불러오기", true),
            new Android_Example_Item(2, "Glide, Picasso, Fresco", true)

    );

    public List<Android_Example_Item> get_image_libraries_list() {
        return this.image_libraries_list;
    }


    //안드로이드 데이터베이스 class List
    private final List<Class> image_libraries_class_list = Arrays.asList(
            ImageLoadActivity.class,         //1
            ImageLibrariesActivity.class   //2
    );

    public List<Class> get_image_libraries_class_list() {
        return this.image_libraries_class_list;
    }

}
