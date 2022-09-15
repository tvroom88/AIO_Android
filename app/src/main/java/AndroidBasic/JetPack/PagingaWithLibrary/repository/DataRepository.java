package AndroidBasic.JetPack.PagingaWithLibrary.repository;

import AndroidBasic.JetPack.PagingaWithLibrary.dao.FriendDAO;
import AndroidBasic.JetPack.PagingaWithLibrary.data.Friend;
import AndroidBasic.JetPack.PagingaWithLibrary.database.FriendDatabase;
import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class DataRepository {

    private FriendDAO friendDAO;

    public DataRepository(Application application){
        FriendDatabase friendDatabase=FriendDatabase.getInstance(application);
        friendDAO=friendDatabase.friendDAO();
    }

    public void insertFriend(final Friend friend){
        FriendDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                friendDAO.insert(friend);
            }
        });
    }

    public LiveData<List<Friend>> getFriendListRepo(){
        return friendDAO.getFriendList();
    }


    // Paging을 적용
    public LiveData<PagedList<Friend>> getPagedList(PagedList.Config config){
        DataSource.Factory<Integer,Friend>factory = friendDAO.getFriendListPaged();
        return new LivePagedListBuilder<>(factory, config)
                .build();
    }


}