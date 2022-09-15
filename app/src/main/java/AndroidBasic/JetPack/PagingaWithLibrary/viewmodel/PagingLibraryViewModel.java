package AndroidBasic.JetPack.PagingaWithLibrary.viewmodel;

import AndroidBasic.JetPack.PagingaWithLibrary.data.Friend;
import AndroidBasic.JetPack.PagingaWithLibrary.repository.DataRepository;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

public class PagingLibraryViewModel extends AndroidViewModel {

    DataRepository repository;

    public static final int PAGE_SIZE = 10; // page size
    public static final int PAGE_INITIAL_LOAD_SIZE_HINT = 20; // page size on first load
    public static final int PAGE_PREFETCH_DISTANCE = 20; // triggers when to fetch a page

    public PagingLibraryViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        repository.insertFriend(new Friend("Sattu"));
    }

    public LiveData<List<Friend>> getFriendListViewModel() {
        return repository.getFriendListRepo();
    }


    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build();

    public LiveData<PagedList<Friend>> getPagedFriendListViewModel(){
        return repository.getPagedList(config);
    };
}
