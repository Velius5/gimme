package zespolowe.pl.aplikacja.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.adapters.SearchFriendsListAdapter;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.FindFriend;
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

/**
 *  Aktywność odpowiedzialna za obsługe wyszukiwania użytkowników oraz dodawanie ich do znajomych
 */

public class Add_Friend_Activity extends AppCompatActivity {
    @Bind(R.id.search_friend)
    EditText _search_friend;//pole wyszukiwania
    @Bind(R.id.btn_znajdz_znajomych)
    Button _btn_znajdz_znajomych;
    ListView list;
    List<FindFriend> findFriends = new ArrayList<>();
    SessionManager session;
    User user;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        ButterKnife.bind(this);

        SearchFriendsListAdapter adapter = new SearchFriendsListAdapter(this, findFriends);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FindFriend Slecteditem = findFriends.get(position);
                sendRequest(Slecteditem);
                Toast.makeText(getBaseContext(), "Dodano znajomego", Toast.LENGTH_LONG).show();


            }

        });

        _btn_znajdz_znajomych.setOnClickListener(new View.OnClickListener() { //guzik wyszukiwania
            @Override
            public void onClick(View v) {
                wyszukiwanie();

            }
        });

    }


    /**
     * Metoda wysyła zapytanie do serwer poprzez API o dodanie wybranego użytkownika do znajomych
     * @param Slecteditem
     */
    public void sendRequest(FindFriend Slecteditem) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<Respon> call = userService.AddFriend(user.getId(), Slecteditem.getId());
        call.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(Call<Respon> call, Response<Respon> response) {
                Respon Slecteditem = response.body();
                System.out.println(Slecteditem);
            }

            @Override
            public void onFailure(Call<Respon> call, Throwable t) {
                System.out.println("Blad.");
            }
        });
    }

    /**
     * Metoda odpowiedzialna za wyszukiwanie użytkowników.
     * Wysyła zapytanie do serwera przez API z danymi szukanego użytkownika.
     * Po otrzymaniu odpowiedzi przekazuje liste znalezionych użytkowników do widoku aplikacji
     */
    public void wyszukiwanie() {
        final String fullname = _search_friend.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

            Call<List<FindFriend>> call = userService.getFindFriendList(user.getId(),fullname);

            call.enqueue(new Callback<List<FindFriend>>() {
                @Override
                public void onResponse(Call<List<FindFriend>> call, Response<List<FindFriend>> response) {
                    List<FindFriend> resp = response.body();
                    for (FindFriend findFriend : resp) {
                        findFriends.add(new FindFriend(findFriend.getId(), findFriend.getName(), findFriend.getSurname()));
                        System.out.println(findFriend.toString());

                    }
                }
                @Override
                public void onFailure(Call<List<FindFriend>> call, Throwable t) {
                    System.out.println("Blad.");
                }
            });

    }
}