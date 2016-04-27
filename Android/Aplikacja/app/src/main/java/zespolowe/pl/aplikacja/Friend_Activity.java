package zespolowe.pl.aplikacja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_main);
        System.out.println("Friend_Activity");


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




        CustomListAdapter adapter=new CustomListAdapter(this, friends);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Friend Slecteditem = friends.get(position);
                Intent intent = new Intent(/*getApplicationContext()*/ Friend_Activity.this, Splac_Activity.class);
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
    }

//    class MyAsyncTask extends AsyncTask<String, String, String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            return null;
//        }
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//        }
//    }
}