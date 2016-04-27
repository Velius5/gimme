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
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.model.Friend;

public class CustomListAdapterWyszukiwarka extends ArrayAdapter {

    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<Friend> friends = new ArrayList<>();

    public CustomListAdapterWyszukiwarka(Activity context, List<Friend> friends) {
        super(context, R.layout.wyszukiwarka_lista_znajomych, friends);

        this.context=context;
        this.friends = friends;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.wyszukiwarka_lista_znajomych, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        SmartImageView imageView = (SmartImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(friends.get(position).getName() + " " + friends.get(position).getSurname());
        imageView.setImageUrl("http://192.168.0.103:8080/userphoto/" + friends.get(position).getId());

        return rowView;

    };

}