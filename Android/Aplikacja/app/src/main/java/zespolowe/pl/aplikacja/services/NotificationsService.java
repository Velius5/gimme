package zespolowe.pl.aplikacja.services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.activities.Menu_Activity;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Notification;

/**
 * Created by Patryk on 2016-05-07.
 */
public class NotificationsService extends GcmTaskService {
    Long userId;

    @Override
    public int onRunTask(TaskParams taskParams) {
        this.setUserId(Long.valueOf(taskParams.getTag()));
        System.out.println("Pobieramy powiadomienia...");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessageService messageService = retrofit.create(MessageService.class);
        Call<Notification> call = messageService.getNotification(getUserId());
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                Notification notification = response.body();
                if(notification != null) {
                    addAndroidNotification(notification.getDate(),notification.getMessage(), notification.getType());
                } else {
                    System.out.println("Brak powiadomień.");
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                System.out.println("Blad pobierania powiadomień.");
            }
        });
        return 0;
    }

    private void addAndroidNotification(String date, String message, String type) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Menu_Activity.class), 0);
        Resources r = getResources();
        int smallIcon = r.getIdentifier("ic_launcher", "drawable", getApplicationContext().getPackageName());
        long[] pattern = {500,500};
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.app.Notification notification = new NotificationCompat.Builder(this)
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
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
