package AndroidAdvanced.MVVM.ViewModel;

import AndroidAdvanced.MVVM.Data.Volume;
import AndroidAdvanced.MVVM.Data.VolumesResponse;
import AndroidAdvanced.MVVM.Repository.BookRepository;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class BookSearchViewModel extends AndroidViewModel {


    private BookRepository bookRepository;
    private LiveData<List<Volume>> volumesResponseLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application.getApplicationContext());
        volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    // ----------------- Room Database part -----------------
    // RoomDB : Loading method
    public void loadAll(){ bookRepository.getAllFromRoom(); }

    // RoomDB : Adding method
    public void insertAll(){
        bookRepository.insertToRoom();
    }

    // RoomDB : Delete method
    public void deleteAll(){ bookRepository.deleteAll(); }
    // ----------------- End Room Database part -----------------


    public void searchVolumes(String keyword, String author, int count) {
        bookRepository.searchVolumes(keyword, author, count);
    }

    public LiveData<List<Volume>> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}
