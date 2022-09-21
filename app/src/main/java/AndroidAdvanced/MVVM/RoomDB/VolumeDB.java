package AndroidAdvanced.MVVM.RoomDB;

import AndroidAdvanced.MVVM.Data.Volume;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Volume.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class VolumeDB extends RoomDatabase {

    private static VolumeDB INSTANCE = null;
    public abstract VolumeDao volumeDao();

    public static VolumeDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VolumeDB.class, "volume.db")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
