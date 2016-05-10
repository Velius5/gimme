package zespolowe.pl.aplikacja.adapters;

import android.app.Activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.HistoryProduct;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;

/**
 * Adapter odpowiedzialny za wygląd oraz działanie listy znajomych
 */
public class HistoryListAdapter extends ArrayAdapter{

    private Activity context;
    private List<HistoryProduct> productList;

    public HistoryListAdapter(Activity context, List<HistoryProduct> productList) {
        super(context, R.layout.history_list_item, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.history_list_item, null, true);

        TextView price = (TextView) rowView.findViewById(R.id.price);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView user = (TextView)rowView.findViewById(R.id.user);

        HistoryProduct historyProduct = productList.get(position);
        Product product = historyProduct.getProduct();
        User activeUser = new SessionManager(context).getUserDetails();

        if(historyProduct.getOwner().equals(activeUser)) price.setTextColor(Color.GREEN);
        else  price.setTextColor(Color.RED);

        price.setText(product.getPricePerPerson().toString() + " zł");
        name.setText(product.getProductName());
        user.setText(historyProduct.getOwner().getName() + " " + historyProduct.getOwner().getSurname());

        String parsedDate = new SimpleDateFormat("dd-MM-yyyy").format(historyProduct.getDate());
        date.setText(parsedDate);


        System.out.println("History row view");
        return rowView;
    }
}
