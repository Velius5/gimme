package zespolowe.pl.aplikacja;
//do listy znajomych
/**
 * Created by Rafał on 2016-04-02.
 */
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;

public class CustomListAdapter extends ArrayAdapter {
    private Activity context;
//    private Friend_Activity.MyAsyncTask context ;
    private List<Friend> friends = new ArrayList<>();

    public CustomListAdapter(Activity context, List<Friend> friends) {
        super(context, R.layout.my_friend_list, friends);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.friends = friends;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_friend_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        SmartImageView imageView = (SmartImageView) rowView.findViewById(R.id.icon);
        TextView balanceTitle = (TextView) rowView.findViewById(R.id.saldo);

        txtTitle.setText(friends.get(position).getName() + " " + friends.get(position).getSurname());
        balanceTitle.setText(String.valueOf(friends.get(position).getBilans() + " zł"));


        if (friends.get(position).getBilans().signum() > 0) {
            balanceTitle.setTextColor(Color.GREEN);
        }
        if (friends.get(position).getBilans().signum() < 0) {
            balanceTitle.setTextColor(Color.RED);
        }


        imageView.setImageUrl(SessionManager.getSERWERURL() + "userphoto/"+ friends.get(position).getId());
//        if(friends.get(position).getPhoto() !=null) {
//            byte[] imageAsBytes = Base64.decode(friends.get(position).getPhoto(), Base64.DEFAULT);
//            imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//        }

        /*extratxt.setText("Description "+itemname[position]);*/
        return rowView;

    };

}