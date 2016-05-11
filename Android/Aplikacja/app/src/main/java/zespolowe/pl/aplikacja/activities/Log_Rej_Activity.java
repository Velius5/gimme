package zespolowe.pl.aplikacja.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.R;

/**
 *  Aktywność odpowiedzialna za obsługe wyboru logowania bądź rejestracji
 */

@SuppressWarnings("ALL")
public class Log_Rej_Activity extends AppCompatActivity {
    private static final String TAG = "Log_Rej_Activity";

    @Bind(R.id.button3)
    Button zaloguj_sie;
    @Bind(R.id.button4)
    Button zarejestuj_sie;

    Context context;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_rej);
        ButterKnife.bind(this);

        System.out.println("Log_Rej_Activity");
        zaloguj_sie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zaloguj_();
            }
        });
        zarejestuj_sie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zarejestuj_();
            }
        });


    }
    /**
     *  Metoda odpowiedzialna za uruchomienie aktywności rejestracji
     */
    private void zarejestuj_() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
    /**
     *  Metoda odpowiedzialna za uruchomienie aktywności logowania
     */
    private void zaloguj_() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
