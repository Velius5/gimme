package zespolowe.pl.aplikacja.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.adapters.CustomListAdapter;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

/**
 *  Aktywność odpowiedzialna za pobieranie i wyświetlanie listy znajomych (zdjęcie, imię, nazwisko, saldo) zalogowanego użytkownika
 */

public class Friend_Activity extends Activity {

    ListView list;
    List<Friend> friends = new ArrayList<>();
    SessionManager session;
    User user;
    Activity act;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_main);
        System.out.println("Friend_Activity");
        System.out.println("postecexute");
        act = this;

        if (friends.isEmpty())
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    System.out.println("Start");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(SessionManager.getAPIURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserService userService = retrofit.create(UserService.class);
                    Call<List<Friend>> call = userService.getFriendList(user.getId());

                    Response<List<Friend>> friend = null;
                    try {
                        friend = call.execute();
                        System.out.println("Pobrano znajomych");
                    } catch (Exception e) {
                        System.out.println("Błąd pobierania danych");
                    }
                    friends = friend.body();

                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);

                    list = (ListView) findViewById(R.id.list);
                    CustomListAdapter adapter = new CustomListAdapter(act, friends);
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            // TODO Auto-generated method stub
                            Friend Slecteditem = friends.get(position);
                            Intent intent = new Intent(/*getApplicationContext()*/ Friend_Activity.this, PayoffFriendDebts_Activity.class);
                            intent.putExtra("saldo", Slecteditem.getBilans());
                            intent.putExtra("name", Slecteditem.getName());
                            intent.putExtra("surname", Slecteditem.getSurname());
                            intent.putExtra("id", Slecteditem.getId());
                            System.out.println(Slecteditem.getId());
                            startActivity(intent);

                        }

                    });

                    System.out.println("koniec");
                }
            }.execute();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Menu_Activity.class);
        startActivity(intent);
    }

}