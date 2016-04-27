package zespolowe.pl.aplikacja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.services.UserService;


public class Produkty_Edycja_Activity extends AppCompatActivity {

    @Bind(R.id.potwierdz_paragon)
    Button _potwierdz_paragon;

    ListView list;
    public List<Product> productList = new ArrayList<>();
    public Receipt receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produkty_edycja);
        ButterKnife.bind(this);

        System.out.println("Produkty_Edycja_Activity");
        Bundle bundle = getIntent().getExtras();
        Long receiptId = (Long) bundle.get("receiptId");
        System.out.println(receiptId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<Receipt> call = userService.getReceipt(receiptId);
        call.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {
                Receipt resp = response.body();
                if (resp != null) {
                    System.out.println(resp.toString());
                    receipt = resp;
                    for (Product product : receipt.getProductList()) {
                        productList.add(product);
                    }
                    CustomListAdapterEdycjaProduktow adapter = new CustomListAdapterEdycjaProduktow(Produkty_Edycja_Activity.this, productList);
                    list = (ListView) findViewById(R.id.lista_produktow_edycja);
                    list.setAdapter(adapter);

                }
            }


            @Override
            public void onFailure(Call<Receipt> call, Throwable t) {
                System.out.println("Blad.");
                //onAddFailed("Zmiana nie powiodła się");
            }
        });

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
