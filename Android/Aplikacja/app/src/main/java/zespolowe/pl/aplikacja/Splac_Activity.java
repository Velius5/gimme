package zespolowe.pl.aplikacja;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

public class Splac_Activity extends AppCompatActivity {
    ListView list;


    //    @Bind(R.id.zaplac) Button _zaplac;
    User user;
    Long friendId;
    List<Product> productList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splac);
        ButterKnife.bind(this);
        Bundle bundle= getIntent().getExtras();
        friendId= (Long) bundle.get("id");
        System.out.println(friendId);


      //  ListAdapterProductsFriends adapter = new ListAdapterProductsFriends(this, productList);
      //  list=(ListView)findViewById(R.id.list);
       // list.setAdapter(adapter);
       // list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Product Slecteditem = productList.get(position);
//                sendRequest(Slecteditem);
//                Toast.makeText(getBaseContext(), "Dodano znajomego", Toast.LENGTH_LONG).show();
//
//
//            }
//
//        });



    }


    private void sendRequest(Product Slecteditem) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<List<Product>> call = userService.getFriendDebtsToMe(user.getId(), friendId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                productList = response.body();
                //System.out.println(Slecteditem);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println("Blad.");
            }
        });
        call = userService.getMyDebtsToFriend(user.getId(), friendId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList.addAll(response.body());
                System.out.println(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println("Blad.");
            }
        });






    }




}