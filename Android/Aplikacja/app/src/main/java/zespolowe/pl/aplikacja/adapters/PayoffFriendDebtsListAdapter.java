package zespolowe.pl.aplikacja.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;
import zespolowe.pl.aplikacja.view.MultiSpinner;

public class PayoffFriendDebtsListAdapter extends ArrayAdapter {
    private Activity context;
    private List<Product> productList = new ArrayList<>();


    public PayoffFriendDebtsListAdapter(Activity context, List<Product> productList) {
        super(context, R.layout.splac, productList);

        this.context = context;
        this.productList = productList;


    }


    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;
        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.splac_lista, null);

        TextView productName = (TextView) rowView.findViewById(R.id.productName);

        productName.setText(productList.get(position).getProductName());



        return rowView;

    }


}