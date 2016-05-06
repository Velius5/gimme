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
import zespolowe.pl.aplikacja.model.Friend;

/**
 * Created by Rafał on 2016-04-27.
 */
public class ListAdapterProductsFriends  extends ArrayAdapter {



        private Activity context;
        //    private Friend_Activity.MyAsyncTask context ;
        private List<Friend> friends = new ArrayList<>();

        public ListAdapterProductsFriends(Activity context, List<Friend> friends) {
            super(context, R.layout.splac_lista, friends);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.friends = friends;
        }

        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.splac_lista, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item);

            txtTitle.setText(friends.get(position).getName() + " " + friends.get(position).getSurname());
//        if(friends.get(position).getPhoto() !=null) {
//            byte[] imageAsBytes = Base64.decode(friends.get(position).getPhoto(), Base64.DEFAULT);
//            imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//        }

        /*extratxt.setText("Description "+itemname[position]);*/
            return rowView;

        };

    }
