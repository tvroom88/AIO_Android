package AndroidBasic.JetPack.PagingaWithLibrary.dao;

import AndroidBasic.JetPack.PagingaWithLibrary.data.Friend;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FriendDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Friend smiley);

    @Query("SELECT * FROM friend_list")
    LiveData<List<Friend>> getFriendList();


    // 이 부분이 paging을 넣는 부분
    @Query("SELECT * FROM friend_list")
    DataSource.Factory<Integer, Friend> getFriendListPaged();

}
