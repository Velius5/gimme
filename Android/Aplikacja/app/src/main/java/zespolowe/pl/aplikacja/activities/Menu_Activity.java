package zespolowe.pl.aplikacja.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.R;


public class Menu_Activity extends AppCompatActivity {
    private static final String TAG = "Menu_Activity";

    @Bind(R.id.imageButton)
    ImageButton camera;

    @Bind(R.id.imageButton2)
    ImageButton gallery;

    @Bind(R.id.imageButton3)
    ImageButton znajomi;

    @Bind(R.id.imageButton4)
    ImageButton profil;

    @Bind(R.id.imageButton6)
    ImageButton ustawienia;

    @Bind(R.id.imageButton5)
    ImageButton historyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {         System.out.println("Menu_Activity");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_karta);
        ButterKnife.bind(this);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera_();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery_();
            }
        });

        znajomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                znajomi_();
            }
        });

        ustawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ustawienia_();
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profil_();
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                history();
            }
        });

    }

    private void camera_() {
        Intent intent = new Intent(this, Camera_Activity.class);
        startActivity(intent);
    }

    private void gallery_() {
        Intent intent = new Intent(this, Gallery_Activity.class);
        startActivity(intent);
    }

    private void znajomi_() {
        Intent intent = new Intent(this, Friend_Activity.class);
        startActivity(intent);
    }
    private void ustawienia_() {
        Intent intent = new Intent(this, Ustawienia.class);
        startActivity(intent);
    }

    private void profil_() {
        Intent intent = new Intent(this, User_Profile_Activity.class);
        startActivity(intent);
    }

    private void history(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }


    private static long back_pressed_time;
    private static long PERIOD = 2000;

    @Override
    public void onBackPressed()
    {
        if (back_pressed_time + PERIOD > System.currentTimeMillis())
          //  super.onBackPressed();
        moveTaskToBack(true);
        else Toast.makeText(getBaseContext(), "Naciśnij dwa razy, aby wyjść.", Toast.LENGTH_SHORT).show();
        back_pressed_time = System.currentTimeMillis();
        Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);//Initiate the vibrate service
        vib.vibrate(100);
    }
}
