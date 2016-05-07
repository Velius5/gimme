package zespolowe.pl.aplikacja.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.adapters.HistoryListAdapter;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.HistoryProduct;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;


public class HistoryActivity extends AppCompatActivity {

    private SessionManager session;
    private User user;
    private ListView historyProductListView;
    private List<HistoryProduct> historyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        historyList = new ArrayList<>();
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SessionManager.getAPIURL())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = retrofit.create(UserService.class);
                Call<List<HistoryProduct>> call = userService.getHistory(user.getId());
                try{
                    Response<List<HistoryProduct>> response = call.execute();
                    historyList = response.body();
                }catch (IOException e){
                    System.out.println(e);
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                HistoryListAdapter adapter = new HistoryListAdapter(HistoryActivity.this,historyList);
                historyProductListView = (ListView) findViewById(R.id.historyListView);
                historyProductListView.setAdapter(adapter);

                System.out.println("History act");
            }
        }.execute();
    }
}
