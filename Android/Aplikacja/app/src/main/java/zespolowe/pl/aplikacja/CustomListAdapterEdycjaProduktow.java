package zespolowe.pl.aplikacja;
//do listy znajomych
/**
 * Created by Rafał on 2016-04-02.
 */

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.model.Product;

public class CustomListAdapterEdycjaProduktow extends ArrayAdapter {
    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<Product> product = new ArrayList<>();
    private List<Product> editedProductList = new ArrayList<>();


    public CustomListAdapterEdycjaProduktow(Activity context, List<Product> product) {
        super(context, R.layout.produkty_edycja_lista, product);
        // TODO Auto-generated constructor stub


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
                editedProductList.get(position).setProductName(s.toString());
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
                editedProductList.get(position).setPrice(BigDecimal.valueOf(Double.valueOf(s.toString())));
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
                editedProductList.get(position).setCount(Double.valueOf(s.toString()));
            }
        });

//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                System.out.println("działa!");
//                return false;
//            }
//        });

        return rowView;

    };



}