package zespolowe.pl.aplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.model.User;


public class Produkty_Activity extends AppCompatActivity {

    //@Bind(R.id.zaplac) Button _zaplac;

    ListView list;
    List<Product> produkty = new ArrayList<>();
    List<String> friendsList = new ArrayList<>();
    List<String> friendsIdsList = new ArrayList<>();
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

        friendsList.add("Ja");
        friendsIdsList.add(String.valueOf(user.getId()));

        Bundle bundle = getIntent().getExtras();
        receipt = (Receipt) bundle.getSerializable("paragon");

        for(Product product: receipt.getProductList()) {
            produkty.add(product);
        }





        CustomListAdapterProdukty adapter = new CustomListAdapterProdukty(Produkty_Activity.this, produkty);
        list = (ListView) findViewById(R.id.lista_produktow);
        list.setAdapter(adapter);
    }


}