package zespolowe.pl.aplikacja.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.adapters.PayoffFriendDebtsListAdapter;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

public class PayoffFriendDebts_Activity extends AppCompatActivity {
    ListView list;


    @Bind(R.id.btn_dlugi_splacone)
    Button _zaplac;
    Long friendId;
    List<Product> productList;
    SessionManager session;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splac);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        Bundle bundle= getIntent().getExtras();
        friendId= (Long) bundle.get("id");
        GetFriendDebtsToMeTask task = new GetFriendDebtsToMeTask();
        task.execute();

    }

    class GetFriendDebtsToMeTask extends AsyncTask<Void, Integer, Void> {
        Retrofit retrofit;
        UserService userService;
        Call<List<Product>> call;
        List<Product> prodList;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                prodList = call.execute().body();
                System.out.println("Pobrano listę produktów");
                if(prodList != null) {
                    for (Product p : prodList) {
                        System.out.println(p.getProductName());
                    }
                }
            } catch (IOException e) {
                System.out.println("Błąd połączenia z serwerem. Nie można pobrać listy produktów.");
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SessionManager.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            userService = retrofit.create(UserService.class);
            call = userService.getFriendDebtsToMe(user.getId(), friendId);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final PayoffFriendDebtsListAdapter adapter = new PayoffFriendDebtsListAdapter(PayoffFriendDebts_Activity.this, prodList);
            list = (ListView) findViewById(R.id.listOfFriendDebtsToMe);
            list.setAdapter(adapter);

            _zaplac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(SessionManager.getAPIURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService userService = retrofit.create(UserService.class);
                    Call<Respon> call = userService.payoffAllFriendDebts(user.getId(), friendId);

                    call.enqueue(new Callback<Respon>() {
                        @Override
                        public void onResponse(Call<Respon> call, Response<Respon> response) {
                            Respon resp = response.body();
                            if(resp != null) {
                                if (resp.getValue() == true) {
                                    Intent intent = new Intent(PayoffFriendDebts_Activity.this, Friend_Activity.class);
                                    startActivity(intent);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Potwierdzono spłatę długów.", Toast.LENGTH_LONG);
                                    toast.show();
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Ten użytkownik nie ma u Ciebie żadnych długów", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Respon> call, Throwable t) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Błąd połączenia z serwerem. Sprawdź połączenie internetowe albo spróbuj później.", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });

                }
            });

        }

    }




}