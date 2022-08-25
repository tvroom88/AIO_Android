package AndroidBasic.Network.Retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface Retrofit_interface {

    @GET("posts/{UserID}")
    Call<data_model> test_api_get(
            @Path("UserID") String userid);

    @GET("posts")
    Call<List<data_model>> get();

    @GET("posts")
    Call<data_model> get2();
}