package zespolowe.pl.aplikacja;

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
import retrofit2.converter.jackson.JacksonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;


public class Produkty_Activity extends AppCompatActivity {

    @Bind(R.id.podziel_koszty_button)
    Button podziel_koszty_button;

    ListView list;
    List<Product> produkty = new ArrayList<>();
    SessionManager session;
    User user;
    public Receipt receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produkty);
        ButterKnife.bind(this);
        System.out.println("Produkty_Activity");


        Bundle bundle = getIntent().getExtras();
        receipt = (Receipt) bundle.getSerializable("paragon");

        for(Product product: receipt.getProductList()) {
            produkty.add(product);
        }

        GetUserFriendsTask task = new GetUserFriendsTask();
        task.execute();



    }

    class GetUserFriendsTask extends AsyncTask<Void, Integer, List<Friend>> {
        Retrofit retrofit;
        UserService userService;
        Call<List<Friend>> call;
        List<Friend> resp = null;
        List<Friend> friends = new ArrayList<>();
        @Override
        protected List<Friend> doInBackground(Void... params) {
            try {
                resp = call.execute().body();
                for (Friend friend : resp) {
                    friends.add(friend);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return friends;
        }

        @Override
        protected void onPreExecute() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SessionManager.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            userService = retrofit.create(UserService.class);
            call = userService.getFriendList(user.getId());
        }

        @Override
        protected void onPostExecute(List<Friend> friends) {
            final CustomListAdapterProdukty adapter = new CustomListAdapterProdukty(Produkty_Activity.this, produkty, friends);
            list = (ListView) findViewById(R.id.lista_produktow);
            list.setAdapter(adapter);

            podziel_koszty_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(SessionManager.getAPIURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    List<String> productsUsersList = new ArrayList<String>();

                    for(Product pr : receipt.getProductList()) {
                        String users = null;
                        if(pr.getUsers() != null) {
                            for (User us : pr.getUsers()) {
                                if (users == null) {
                                    users = String.valueOf(pr.getId());
                                }
                                users += ',';
                                users += us.getId();
                            }
                            productsUsersList.add(users);
                            System.out.println(users);
                        } else {
                            productsUsersList.add(String.valueOf(pr.getId()));
                        }
                    }

                    UserService userService = retrofit.create(UserService.class);
                    Call<Respon> call = userService.editReceipt(receipt.getId(), productsUsersList);

                    call.enqueue(new Callback<Respon>() {
                        @Override
                        public void onResponse(Call<Respon> call, Response<Respon> response) {
                            Respon resp = response.body();
                            if(resp != null) {
                                if (resp.getValue() == true) {
                                    Intent intent = new Intent(Produkty_Activity.this, Menu_Activity.class);
                                    startActivity(intent);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Paragon został pomyślnie zapisany a koszty podzielone.", Toast.LENGTH_LONG);
                                    toast.show();
                                } else {
                                    receiptAddingFailure();
                                    System.out.println("blad1");
                                }
                            } else {
                                receiptAddingFailure();
                                System.out.println("blad2");
                            }
                        }

                        @Override
                        public void onFailure(Call<Respon> call, Throwable t) {
                            receiptAddingFailure();
                            System.out.println("blad3");
                        }
                    });

                }
            });
        }
        private void receiptAddingFailure() {
            Toast toast = Toast.makeText(getApplicationContext(), "Problem z wysłaniem paragonu. Sprawdź połączenie internetowe.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}