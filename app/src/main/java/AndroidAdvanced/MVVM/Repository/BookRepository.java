package AndroidAdvanced.MVVM.Repository;

import AndroidAdvanced.MVVM.Data.Volume;
import AndroidAdvanced.MVVM.Data.VolumesResponse;
import AndroidAdvanced.MVVM.RoomDB.VolumeDB;
import AndroidAdvanced.MVVM.RoomDB.VolumeDao;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BookRepository {

    // Room 부분
    private VolumeDao volumeDao;

    // Retrofit 부분
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";
    private BookSearchService bookSearchService;

    // LiveData 부분
//    private MutableLiveData<VolumesResponse> volumesResponseLiveData;
    private MutableLiveData<List<Volume>> volumesResponseLiveData;


    public BookRepository(Context context) {

        // Room Dao
        volumeDao = VolumeDB.getInstance(context).volumeDao();

        // Live Data
        volumesResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }

    // -------- Room 데이터베이스 --------
    // Load All data
    public List<Volume> getAllFromRoom() {
        AtomicReference<List<Volume>> volumeList = new AtomicReference<>();
        try {
            Thread t1 = new Thread(() -> {
                volumeList.set(volumeDao.getAll());
//                return volumeList.get();
            });
            t1.start();
            t1.join();
            return volumeList.get();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
//        return volumeDao.getAll();
//        return null;
    }

    // Insert All data
    public void insertToRoom(Volume... volumes) {
        try {
            Thread t1 = new Thread(() -> {
                volumeDao.insertAll(volumes);
//                volumesResponseLiveData.postValue(getAllFromRoom());
            }

            );
            t1.start();
            t1.join();
//            volumesResponseLiveData.postValue(getAllFromRoom());

        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Delete All data
    public void deleteAll(){
        try {
            Thread t1 = new Thread(() -> volumeDao.deleteAll());
            t1.start();
            t1.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    // -------- Room 데이터베이스 끝 --------

    // Retrofit으로 자료 받아와서 업데이트 하는 부분
    public void searchVolumes(String keyword, String author, int count) {
        bookSearchService.searchVolumes(keyword, author)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {

                            volumesResponseLiveData.postValue(response.body().getItems());

                            // RoomDB에 이전 데이터는 지워주고 새로운 데이터들로 연결
//                            deleteAll();
//                            insertToRoom(response.body().getItems().toArray(new Volume[0]));
//
//                            if(count == 1){
//                                //Retrofit과 연결해서 바로 연결시켜줄때
//                                volumesResponseLiveData.postValue(response.body().getItems());
//                                Log.e("hi", "1");
//                            } else {
//                                // Room에서 불러온들 Live Data에 넘겨주기
//                                volumesResponseLiveData.postValue(getAllFromRoom());
//                                Log.e("hi", "2");
//                            }


                        }
                    }
                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        Log.e("hi", "3");
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

//    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
//        return volumesResponseLiveData;
//    }

        public LiveData<List<Volume>> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }


}
