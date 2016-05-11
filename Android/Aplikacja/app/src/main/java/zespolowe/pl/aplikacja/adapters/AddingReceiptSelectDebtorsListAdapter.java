package zespolowe.pl.aplikacja.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.view.MultiSpinner;

public class AddingReceiptSelectDebtorsListAdapter extends ArrayAdapter implements MultiSpinner.MultiSpinnerListener {
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

    public AddingReceiptSelectDebtorsListAdapter(Activity context, List<Product> product, List<Friend> friendList) {
        super(context, R.layout.produkty_lista, product);
        // TODO Auto-generated constructor stub
        session = new SessionManager(context);
        user = session.getUserDetails();

        this.context = context;
        this.productList = product;
        this.friendList = friendList;


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



        return rowView;

    }


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