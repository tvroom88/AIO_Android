package AndroidAdvanced.RetrofitAndRecycler.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Picsum_Model.class}, version = 1, exportSchema = false)
public abstract class PicsumDB extends RoomDatabase {

    private static PicsumDB INSTANCE = null;
    public abstract PicsumDao picsumDao();

    public static PicsumDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PicsumDB.class, "picsum.db")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
