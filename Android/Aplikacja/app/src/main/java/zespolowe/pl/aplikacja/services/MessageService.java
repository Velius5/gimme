package zespolowe.pl.aplikacja.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zespolowe.pl.aplikacja.model.Notification;

/**
 *  Klasa odpowiedzialna za obsługę powiadomień z API
 */

public interface MessageService  {

    @GET("notifications/{id_uzytkownika}")//pobieranie ostatniego nieprzeczytanego powiadomienia użytkownika
    Call<Notification> getNotification(@Path("id_uzytkownika") Long id);

}
