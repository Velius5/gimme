package zespolowe.pl.aplikacja.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zespolowe.pl.aplikacja.model.User;

/**
 * Created by Rafał on 2016-04-11.
 */
public interface UserService {
    @GET("user/{id}")
    Call<User> getUser(@Path("id") Long id);

    @POST("login")
    Call<User> loginUser(@Query("email") String email, @Query("haslo") String password);

    @POST("register")
    Call<User> registerUser(@Query("email") String email, @Query("haslo") String password);
}
