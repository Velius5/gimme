package zespolowe.pl.aplikacja.adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.model.Product;

/**
 * Created by Piotr on 2016-05-03.
 */
public class HistoryListAdapter extends ArrayAdapter{


    public HistoryListAdapter(Activity context, List<Product> productList) {
        super(context, R.layout.history_list_item, productList);
    }
}
