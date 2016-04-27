package zespolowe.pl.aplikacja;
//do listy znajomych
/**
 * Created by Rafał on 2016-04-02.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.model.Product;

public class CustomListAdapterEdycjaProduktow extends ArrayAdapter {
    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<Product> product = new ArrayList<>();


    public CustomListAdapterEdycjaProduktow(Activity context, List<Product> product) {
        super(context, R.layout.produkty_edycja_lista, product);
        // TODO Auto-generated constructor stub


        this.context=context;
        this.product = product;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.produkty_edycja_lista, null, true);

        EditText produkt = (EditText) rowView.findViewById(R.id.product_edit_name);
        EditText price = (EditText) rowView.findViewById(R.id.product_edit_price);
        EditText count = (EditText) rowView.findViewById(R.id.product_edit_count);


        produkt.setText(product.get(position).getProductName());
        price.setText((product.get(position).getPrice() + " zł"));
        count.setText(product.get(position).getCount());

        return rowView;

    };

}