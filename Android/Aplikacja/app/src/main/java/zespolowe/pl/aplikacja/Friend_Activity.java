package zespolowe.pl.aplikacja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Rafał on 2016-04-02.
 */

//TODO Dodaj: Po kliknięciu na zdjecie wyswietla sie jego profil a w min jest zdjęcie dane personalne oraz dwa okna z danymi (+,-)i do nich
    //TODO:oraz przy nich przycisk do spłaty długu(SPŁATA):Po kliknieciu na niego wyswietli sie opcja wysyłanie zapłaty (w formie okna liczbowego i i przycisku przeslij )
        //TODO: po tej akcj idzie zapytanie boolowskie do osoby ktora moze to potwierdzic jesli tak to zwaca na ok(1) i u nas wyswietla sie powiadomienie
    //TODO:po kliknięciu w dlugi(-) lub w plusy da na liste przedmiotów (przy minusach długi lub  prz plusach na nasząkorzyśc)
public class Friend_Activity extends Activity {

    ListView list;
    String[] itemname ={
            "Robert Zając",
            "Robert Zaj2222ąc",
            "Robert Zajew44ąc",
            "Robert Zajrerąc",
            "Robert ddZając",
            "Robert Zadehhhhhhhrąc",
            "Robert Zaj1311111111ąc",
            "Robert Zaj1112333333ąc"
    };

    Integer[] imgid={
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
            R.drawable.pic1,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_main);
        System.out.println("Friend_Activity");

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}