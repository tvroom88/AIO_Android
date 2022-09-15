package AndroidBasic.JetPack.PagingaWithLibrary;

import AndroidBasic.JetPack.PagingaWithLibrary.adapter.FriendListNormalAdapter;
import AndroidBasic.JetPack.PagingaWithLibrary.adapter.FriendListPagedAdapter;
import AndroidBasic.JetPack.PagingaWithLibrary.data.Friend;
import AndroidBasic.JetPack.PagingaWithLibrary.viewmodel.PagingLibraryViewModel;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.PagingLibraryActivityBinding;

import java.util.List;

public class PagingLibraryActivity extends BaseActivity {

    private PagingLibraryActivityBinding mBinding;
    final String TITLE = "Paging 예제 1";


    PagingLibraryViewModel pagingLibraryViewModel;
    RecyclerView recyclerView;
//    FriendListNormalAdapter adapter;
    FriendListPagedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = PagingLibraryActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        recyclerView = mBinding.friendRecyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pagingLibraryViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(PagingLibraryViewModel.class);
        adapter=new FriendListPagedAdapter();

        pagingLibraryViewModel.getPagedFriendListViewModel().observe(this, new Observer<PagedList<Friend>>() {
            @Override
            public void onChanged(PagedList<Friend> friends) {
                adapter.submitList(friends);
                recyclerView.setAdapter(adapter);
            }
        });

//        pagingLibraryViewModel.getFriendListViewModel().observe(this, new Observer<List<Friend>>() {
//            @Override
//            public void onChanged(List<Friend> friends) {
//                adapter = new FriendListNormalAdapter(PagingLibraryActivity.this, friends);
//                recyclerView.setAdapter(adapter);
//            }
//        });
    }
}