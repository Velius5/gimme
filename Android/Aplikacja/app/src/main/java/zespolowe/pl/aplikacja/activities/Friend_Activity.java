package zespolowe.pl.aplikacja.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
 * Created by Rafał on 2016-04-02.
 */

//TODO Dodaj: Po kliknięciu na zdjecie wyswietla sie jego profil a w min jest zdjęcie dane personalne oraz dwa okna z danymi (+,-)i do nich
//TODO:oraz przy nich przycisk do spłaty długu(SPŁATA):Po kliknieciu na niego wyswietli sie opcja wysyłanie zapłaty (w formie okna liczbowego i i przycisku przeslij )
//TODO: po tej akcj idzie zapytanie boolowskie do osoby ktora moze to potwierdzic jesli tak to zwaca na ok(1) i u nas wyswietla sie powiadomienie
//TODO:po kliknięciu w dlugi(-) lub w plusy da na liste przedmiotów (przy minusach długi lub  prz plusach na nasząkorzyśc)
public class Friend_Activity extends Activity {

    ListView list;
    List<Friend> friends = new ArrayList<>();
    SessionManager session;
    User user;
    Activity act;

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
                            //  Intent intent = new Intent(this, User_Profile_Activity.class)
                            //    startActivity(intent);
                            // Toast.makeText(getApplicationContext(), (CharSequence) Slecteditem, Toast.LENGTH_SHORT).show();

                        }

                    });
                    System.out.println("koniec");
                }
            }.execute();
    }
}