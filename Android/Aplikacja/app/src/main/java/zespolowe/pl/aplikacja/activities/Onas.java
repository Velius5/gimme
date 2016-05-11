package zespolowe.pl.aplikacja.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.R;
/**
 *  Aktywność odpowiedzialna za obsługe aktywności o nas
 */

public class Onas extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_nas);
        ButterKnife.bind(this);
    }
}