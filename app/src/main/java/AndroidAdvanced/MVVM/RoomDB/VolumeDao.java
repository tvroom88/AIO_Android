package AndroidAdvanced.MVVM.RoomDB;

import AndroidAdvanced.MVVM.Data.Volume;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface VolumeDao {
    @Query("SELECT * FROM volume")
    List<Volume> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Volume... volumes);


    @Query ("DELETE FROM volume")
    void deleteAll();

}
