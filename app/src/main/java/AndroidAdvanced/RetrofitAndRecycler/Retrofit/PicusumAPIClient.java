package AndroidAdvanced.RetrofitAndRecycler.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicusumAPIClient {

    private static final String BASE_URL = "https://picsum.photos/v2/";

    // PicusmAPIService - interface
    public static PicsumAPIService getApiService(){
        return getInstance().create(PicsumAPIService.class);
    }

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
