/*
package zespolowe.pl.aplikacja.functions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

*/
/**
 * Created by Rafał on 2016-04-11.
 *//*

public class APIConnector {
    Retrofit retrofit;
    public User user;

    public APIConnector() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("umk-zespolowka-2016.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public User loginUser(String email, String password) {
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.loginUser(email, password);
        try {
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Respon<User> response) {
                    User usera = response.body();
                    APIConnector.this.user = usera;
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Log error here since request failed
                }
            });
            return user;
        } catch (Exception ex) {
            return new User();
        }
    }
/////////////////////////
    public User registerUser(String email, String password) {
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.registerUser(email, password);
        try {
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Respon<User> response) {
                    User usera = response.body();
                    APIConnector.this.user = usera;
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Log error here since request failed
                }
            });
            return user;
        } catch (Exception ex) {
            return new User();
        }
    }
/////////////////////////////////
    public User getUser(Long id) {
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.getUser(id);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Respon<User> response) {
                    User usera = response.body();
                    user = new User(usera.getName(), usera.getSurname(), usera.getEmail(), usera.isActive(), usera.getRole());
                    System.out.println(usera.toString());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Log error here since request failed
                }
            });
            return user;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
*/
