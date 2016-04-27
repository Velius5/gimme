package zespolowe.pl.aplikacja;
//do listy znajomych
/**
 * Created by Rafał on 2016-04-02.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
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
import zespolowe.pl.aplikacja.view.MultiSpinner.MultiSpinnerListener;

public class CustomListAdapterProdukty extends ArrayAdapter implements MultiSpinnerListener {
    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<Product> productList = new ArrayList<>();
    public List<Friend> friendList = new ArrayList<>();
    SessionManager session;
    User user;
    public List<Product> prodList = new ArrayList<>();


    class ViewHolder {
        MultiSpinner spinner;
        TextView product;
    }

    public CustomListAdapterProdukty(Activity context, List<Product> product) {
        super(context, R.layout.produkty_lista, product);
        // TODO Auto-generated constructor stub
        session = new SessionManager(context);
        user = session.getUserDetails();

        this.context = context;
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
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                System.out.println("Blad.");
            }
        });

    }

    public View getView(int position, View view, ViewGroup parent) {


        View rowView = view;
        prodList = productList;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.produkty_lista, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.product = (TextView) rowView.findViewById(R.id.product_name);
            viewHolder.spinner = (MultiSpinner) rowView.findViewById(R.id.spinner);
            viewHolder.spinner.setTag(position);
            rowView.setTag(viewHolder);

        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.product.setText(productList.get(position).getProductName());
        viewHolder.spinner.setItems(friendList, "Wybierz", this, prodList.get(position));



//            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Integer realRowId = (Integer) parent.getTag();
//                    MultiSpinner ms = (MultiSpinner) parent.getItemAtPosition(position);
//                    List<Friend> selectedFriends = ms.getSelectedFriends();
//                    Integer pos = realRowId;
//                    System.out.println(ms.getProduct().toString());
//                    if (selectedFriends == null) {
//                        return;
//                    }
//
//                    final List<User> selectedUsers = new ArrayList<User>();
//                    selectedUsers.clear();
//                    for (Friend f : selectedFriends) {
//                        System.out.println(f.toString());
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl(SessionManager.getAPIURL())
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//
//                        UserService userService = retrofit.create(UserService.class);
//                        Call<User> call = userService.getUser(f.getId());
//                        call.enqueue(new Callback<User>() {
//                            @Override
//                            public void onResponse(Call<User> call, Response<User> response) {
//                                User usr = response.body();
//                                if(usr != null) {
//                                    selectedUsers.add(usr);
//                                    System.out.println(usr.toString());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<User> call, Throwable t) {
//                                System.out.println("Blad.");
//                                //onAddFailed("Zmiana nie powiodła się");
//                            }
//                        });
//
//
//                    }
//                    prodList.get(pos).setUsers(selectedUsers);
//                    //System.out.println(productList.get(pos).toString());
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });


        return rowView;

    }

    ;

    @Override
    public void onItemsSelected(boolean[] selected, List<Friend> selectedFriends, Product product) {
        List<Friend> selectedFriendsList = new ArrayList<>();
        final List<User> selectedUsers = new ArrayList<>();
        Product getProd;
            for (int i = 0; i < friendList.size(); i++) {
                if(selected[i] == true) {
                    selectedFriendsList.add(friendList.get(i));
                }
            }


        for(Friend f : selectedFriendsList) {
            User user = new User(f.getId(), f.getName(), f.getSurname());
            if(user != null) {
                selectedUsers.add(user);
                System.out.println(user);
            }

        }
        prodList.get(prodList.indexOf(product)).setUsers(selectedUsers);
        for(Product p : prodList) {
            System.out.println(p.toString());
        }

    }
}