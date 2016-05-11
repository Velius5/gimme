package zespolowe.pl.aplikacja.adapters;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.model.Product;
/**
 * Adapter odpowiedzialny za wygląd oraz działanie listy edycji produktów
 */
public class AddingReceiptEditListAdapter extends ArrayAdapter {
    private Activity context;
    private List<Product> product = new ArrayList<>();
    private List<Product> editedProductList = new ArrayList<>();


    public AddingReceiptEditListAdapter(Activity context, List<Product> product) {
        super(context, R.layout.produkty_edycja_lista, product);

        this.context=context;
        this.product = product;
        this.editedProductList = product;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.produkty_edycja_lista, null, true);

        EditText produkt = (EditText) rowView.findViewById(R.id.product_edit_name);
        EditText price = (EditText) rowView.findViewById(R.id.product_edit_price);
        EditText count = (EditText) rowView.findViewById(R.id.product_edit_count);


        produkt.setText(editedProductList.get(position).getProductName());
        price.setText(editedProductList.get(position).getPrice().toString());
        count.setText(editedProductList.get(position).getCount());

        produkt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    editedProductList.get(position).setProductName(s.toString());
                } catch (Exception ex) {

                }
            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    editedProductList.get(position).setPrice(BigDecimal.valueOf(Double.valueOf(s.toString())));
                } catch (Exception ex) {

                }
            }
        });

        count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    editedProductList.get(position).setCount(Double.valueOf(s.toString()));
                } catch (Exception ex) {

                }
            }
        });

        return rowView;

    };



}