package shop.com.simpleshopper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vikash on 1/29/2017.
 */

public interface ApiInterface {
    @GET("/fetch-all-fish.php")
    Call<List<Product>> getTopRatedMovies();

}