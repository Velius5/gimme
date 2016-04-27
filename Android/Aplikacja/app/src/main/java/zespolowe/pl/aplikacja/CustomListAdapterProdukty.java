package zespolowe.pl.aplikacja;
//do listy znajomych
/**
 * Created by Rafał on 2016-04-02.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;
import zespolowe.pl.aplikacja.view.MultiSpinner;

public class CustomListAdapterProdukty extends ArrayAdapter implements MultiSpinner.MultiSpinnerListener {
    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<Product> productList = new ArrayList<>();
    public List<Friend> friendList = new ArrayList<>();
    SessionManager session;
    User user;
    public List<User> usersList = new ArrayList<>();

    public CustomListAdapterProdukty(Activity context, List<Product> product) {
        super(context, R.layout.produkty_lista, product);
        // TODO Auto-generated constructor stub
        session = new SessionManager(context);
        user = session.getUserDetails();

        this.context=context;
        this.productList = product;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<List<Friend>> call = userService.getFriendList(user.getId());
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                List<Friend> resp = response.body();
                //friends = resp;
                for (Friend friend : resp) {
                    friendList.add(friend);
                }
                //onAddSuccess();
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                System.out.println("Blad.");
                //onAddFailed("Zmiana nie powiodła się");
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.produkty_lista, null, true);

        final TextView produkt = (TextView) rowView.findViewById(R.id.product_name);
        MultiSpinner ms = (MultiSpinner) rowView.findViewById(R.id.spinner);

        produkt.setText(productList.get(position).getProductName());
        ms.setItems(friendList, "Wybierz", this, productList.get(position));
        ms.setTag(position);


        ms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MultiSpinner ms = (MultiSpinner) rowView.findViewById(R.id.spinner);
                Integer pos = (Integer) ms.getTag();
                System.out.println(ms.getProduct().toString());
                List<Friend> selectedElements = ms.getSelectedItems();

                System.out.println(friendList.get(position).toString());
                usersList.clear();
                for(Friend f : selectedElements) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(SessionManager.getAPIURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService userService = retrofit.create(UserService.class);
                    Call<User> call = userService.getUser(f.getId());
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User usr = response.body();
                            usersList.add(usr);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            System.out.println("Blad.");
                            //onAddFailed("Zmiana nie powiodła się");
                        }
                    });


                }
                productList.get(pos).setUsers(usersList);
                System.out.println(productList.get(pos).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rowView;

    };

    @Override
    public void onItemsSelected(boolean[] selected) {

    }

}