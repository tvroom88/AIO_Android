package AndroidBasic.ImageLibraries;

import AndroidExampleAdapter.ListView_Type_One_Adapter;
import AndroidExampleModel.ImageLibraries_Model;
import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.ImageLibrariesListBinding;

public class ImageLibraries_List extends BaseActivity {

    private ImageLibrariesListBinding mBinding;
    final String TITLE = "이미지 라이브러리 예제 리스트";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ImageLibrariesListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ListView_Type_One_Adapter adapter = new ListView_Type_One_Adapter(
                ImageLibraries_Model.getInstance().get_image_libraries_list(),
                ImageLibraries_Model.getInstance().get_image_libraries_class_list()
        );

        mBinding.listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);
    }
}