package zespolowe.pl.aplikacja.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.adapters.HistoryDetailsListAdapter;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.HistoryProduct;
import zespolowe.pl.aplikacja.model.User;

public class ProductHistoryDetails extends AppCompatActivity {

    private ListView usersListView;
    private HistoryProduct product;

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
        if(!activeUser.equals(owner))
            divider = -1;
        else divider = product.getPaidUsers().size();

        List<User> users = product.getPaidUsers();
        users.addAll(product.getUsers());


        BaseAdapter listAdapter = new HistoryDetailsListAdapter(ProductHistoryDetails.this,users,divider);
        usersListView.setAdapter(listAdapter);
    }
}
