package zespolowe.pl.aplikacja.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.activities.LoginActivity;
import zespolowe.pl.aplikacja.activities.Menu_Activity;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Notification;
import zespolowe.pl.aplikacja.model.User;

/**
 * Created by Patryk on 2016-05-11.
 */
public class NotificationsReceiver extends BroadcastReceiver {
    static User user;
    Context context;
    Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        System.out.println("NotificationsReceiver!");
        if(user != null) {
            new AsyncTask<Void, Void, Void>() {
                Retrofit retrofit;
                MessageService messageService;
                Call<Notification> call;
                Notification notification;

                @Override
                protected Void doInBackground(Void... params) {

                    try {
                        notification = call.execute().body();
                        if (notification != null) {
                            addAndroidNotification(notification.getDate(), notification.getMessage(), notification.getType());
                        } else {
                            System.out.println("Brak powiadomień.");
                        }
                    } catch (IOException e) {
                        System.out.println("Blad pobierania powiadomień.");
                    }

                    return null;
                }

                @Override
                protected void onPreExecute() {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(SessionManager.getAPIURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    messageService = retrofit.create(MessageService.class);
                    call = messageService.getNotification(getUser().getId());
                    System.out.println("Pobieramy powiadomienia...");

                }

                @Override
                protected void onPostExecute(Void result) {
                    System.out.println("Koniec pobierania powiadomień.");
                }
            }.execute();
        }

    }

    void addAndroidNotification(String date, String message, String type) {
        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, Menu_Activity.class), 0);
        Resources r = context.getResources();
        int smallIcon = r.getIdentifier("ic_launcher", "drawable", context.getPackageName());
        long[] pattern = {500,500};
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.app.Notification notification = new NotificationCompat.Builder(context)
                .setTicker(message)
                .setSmallIcon(smallIcon)
                .setContentTitle("Gimme")
                .setContentText(message)
                .setContentIntent(pi)
                .setSound(alarmSound)
                .setVibrate(pattern)
                .setAutoCancel(true)
                .build();

        Random generator = new Random();
        int notificationId = generator.nextInt(1000) + 1;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }


    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        NotificationsReceiver.user = user;
    }
}

