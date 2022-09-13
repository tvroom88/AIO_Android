package AndroidAdvanced.RetrofitAndRecycler.Retrofit;

import AndroidAdvanced.RetrofitAndRecycler.DB.Picsum_Model;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PicsumAPIService {

    @GET("list")
    Call<List<Picsum_Model>> getPicsumList();

//    @GET("posts/{UserID}")
//    Call<data_model> test_api_get(
//            @Path("UserID") String userid);
//
//    @GET("posts")
//    Call<List<data_model>> get();
//
//    @GET("posts")
//    Call<data_model> get2();
}
