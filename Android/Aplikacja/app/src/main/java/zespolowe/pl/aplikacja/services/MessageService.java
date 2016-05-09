package zespolowe.pl.aplikacja.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zespolowe.pl.aplikacja.model.Notification;
import zespolowe.pl.aplikacja.model.User;

/**
 * Created by Patryk on 2016-05-07.
 */
public interface MessageService  {

    @GET("notifications/{id_uzytkownika}")//pobieranie ostatniego nieprzeczytanego powiadomienia użytkownika
    Call<Notification> getNotification(@Path("id_uzytkownika") Long id);

}
