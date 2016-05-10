package zespolowe.pl.aplikacja.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zespolowe.pl.aplikacja.R;

/**
 *  Aktywność odpowiedzialna za wystartowanie aplikacji oraz uruchomienie aktywności logowania bądź rejestracji
 */

public class MainActivity extends AppCompatActivity {

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity");


        startActivity(new Intent(MainActivity.this, Log_Rej_Activity.class));
        finish();

    }

}

