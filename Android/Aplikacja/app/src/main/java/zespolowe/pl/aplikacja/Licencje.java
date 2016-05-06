package zespolowe.pl.aplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.functions.SessionManager;

public class Licencje extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licencje);
        ButterKnife.bind(this);
    }
}