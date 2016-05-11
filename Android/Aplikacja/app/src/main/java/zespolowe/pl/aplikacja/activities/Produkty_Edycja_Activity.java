package zespolowe.pl.aplikacja.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.adapters.AddingReceiptEditListAdapter;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.services.UserService;

/**
 *  Aktywność odpowiedzialna za ewentualną korektę błędów w produktach z paragonu
 */
public class Produkty_Edycja_Activity extends AppCompatActivity {

    @Bind(R.id.potwierdz_paragon)
    Button _potwierdz_paragon;
    Long receiptId;
    ListView list;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produkty_edycja);
        ButterKnife.bind(this);

        System.out.println("Produkty_Edycja_Activity");
        Bundle bundle = getIntent().getExtras();
        receiptId = (Long) bundle.get("receiptId");
        System.out.println(receiptId);

        GetReceiptFromAPITask task = new GetReceiptFromAPITask();
        task.execute();



    }
    /**
     *  Metoda wymienia dane z serwerem
     */

    private class GetReceiptFromAPITask extends AsyncTask<Void, Integer, Void> {
        Retrofit retrofit;
        UserService userService;
        Call<Receipt> call;
        Receipt receipt;
        List<Product> productList;
        protected Void doInBackground(Void... params) {
            try {
                receipt = call.execute().body();
                productList = receipt.getProductList();
                System.out.println(receipt.toString());
            } catch (IOException e) {
                System.out.println("Błąd pobierania paragonu. OCR źle go odczytał.");
            }
            return null;
        }


        protected void onPreExecute() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SessionManager.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            userService = retrofit.create(UserService.class);
            call = userService.getReceipt(receiptId);

        }

        @Override
        protected void onPostExecute(Void result)
        {
            AddingReceiptEditListAdapter adapter = new AddingReceiptEditListAdapter(Produkty_Edycja_Activity.this, productList);
            list = (ListView) findViewById(R.id.lista_produktow_edycja);
            list.setAdapter(adapter);

            _potwierdz_paragon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Produkty_Edycja_Activity.this, Produkty_Activity.class);
                    receipt.setProductList(productList);
                    intent.putExtra("paragon", receipt);
                    startActivity(intent);
                }
            });
        }
    }


}
