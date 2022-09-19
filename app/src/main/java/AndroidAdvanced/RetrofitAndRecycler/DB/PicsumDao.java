package AndroidAdvanced.RetrofitAndRecycler.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PicsumDao {

    @Query("SELECT * FROM picsum")
    List<Picsum_Model> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Picsum_Model... picsum_models);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Picsum_Model picsum_model);

    @Query ("DELETE FROM picsum")
    void deleteAll();

//    @Insert
//    void insert(List<Picsum_Model> picsum_model);
}

