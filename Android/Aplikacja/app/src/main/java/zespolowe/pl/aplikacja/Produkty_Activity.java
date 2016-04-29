package zespolowe.pl.aplikacja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;


public class Produkty_Activity extends AppCompatActivity {

    //@Bind(R.id.zaplac) Button _zaplac;

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
            CustomListAdapterProdukty adapter = new CustomListAdapterProdukty(Produkty_Activity.this, produkty, friends);
            list = (ListView) findViewById(R.id.lista_produktow);
            list.setAdapter(adapter);
        }
    }


}