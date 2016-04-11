package zespolowe.pl.aplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zespolowe.pl.aplikacja.functions.APIConnector;
import zespolowe.pl.aplikacja.model.User;

/**
 * Created by Rafał on 2016-04-11.
 */
public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpclientexample);

        APIConnector api = new APIConnector();

            //User user = api.loginUser("kontakt@patrykdobrzynski.eu", "e10adc3949ba59abbe56e057f20f883e");
            User user = api.getUser(new Long("1"));
            //System.out.println(user.toString());


    }
}
