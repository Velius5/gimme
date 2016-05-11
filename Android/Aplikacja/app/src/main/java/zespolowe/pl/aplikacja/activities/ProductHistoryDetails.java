package zespolowe.pl.aplikacja.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.adapters.HistoryDetailsListAdapter;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.HistoryProduct;
import zespolowe.pl.aplikacja.model.User;

/**
 *  Aktywność wyświetla listę osób oznaczonych w danych produkcie
 */

public class ProductHistoryDetails extends AppCompatActivity {

    private ListView usersListView;
    private HistoryProduct product;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_history_details);
        usersListView = (ListView) findViewById(R.id.usersListView);

        Bundle bundle = getIntent().getExtras();
        product = (HistoryProduct) bundle.getSerializable("product");

        int divider;
        User activeUser = new SessionManager(this).getUserDetails();
        User owner = product.getOwner();
        if(!(activeUser.getId() == owner.getId()))
            divider = -1;
        else divider = product.getPaidUsers().size();

        List<User> users = product.getPaidUsers();
        users.addAll(product.getUsers());


        BaseAdapter listAdapter = new HistoryDetailsListAdapter(ProductHistoryDetails.this,users,divider);
        usersListView.setAdapter(listAdapter);
    }
}

