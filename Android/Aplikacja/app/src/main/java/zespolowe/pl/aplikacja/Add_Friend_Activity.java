package zespolowe.pl.aplikacja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;


public class Add_Friend_Activity extends AppCompatActivity {
    @Bind(R.id.search_friend) EditText _search_friend;//pole wyszukiwania
    @Bind(R.id.btn_znajdz_znajomych) Button _btn_znajdz_znajomych;
    ListView list;
    List<Friend> friends = new ArrayList<>();
    SessionManager session;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<List<Friend>> call = userService.getFriendList(user.getId());
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                List<Friend> resp = response.body();
                //friends = resp;
                for (Friend friend : resp) {
                    friends.add(new Friend(friend.getId(), friend.getName(), friend.getSurname(), friend.getBilans()));
                    System.out.println(friend.toString());
                }
                //onAddSuccess();
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                System.out.println("Blad.");
                //onAddFailed("Zmiana nie powiodła się");
            }
        });

        CustomListAdapterWyszukiwarka adapter=new CustomListAdapterWyszukiwarka(this, friends);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Friend Slecteditem = friends.get(position);
                Intent intent = new Intent(/*getApplicationContext()*/ Add_Friend_Activity.this, Splac_Activity.class);
                intent.putExtra("name", Slecteditem.getName());
                intent.putExtra("surname", Slecteditem.getSurname());
                intent.putExtra("id", Slecteditem.getId());
                System.out.println(Slecteditem.getId());
                startActivity(intent);
            }

        });

        _btn_znajdz_znajomych.setOnClickListener(new View.OnClickListener() { //guzik wyszukiwania
            @Override
            public void onClick(View v) {
                //TODO: dodaj link do About as
            }
        });

    }
}