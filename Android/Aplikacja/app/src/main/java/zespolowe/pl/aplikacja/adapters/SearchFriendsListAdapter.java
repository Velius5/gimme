package zespolowe.pl.aplikacja.adapters;
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

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.FindFriend;

public class SearchFriendsListAdapter extends ArrayAdapter {

    private Activity context;
    //    private Friend_Activity.MyAsyncTask context ;
    private List<FindFriend> findFriends = new ArrayList<>();

    public SearchFriendsListAdapter(Activity context, List<FindFriend> findFriends) {
        super(context, R.layout.wyszukiwarka_lista_znajomych, findFriends);

        this.context=context;
        this.findFriends = findFriends;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.wyszukiwarka_lista_znajomych, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        SmartImageView smartImageView = (SmartImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(findFriends.get(position).getName()+" "+ findFriends.get(position).getSurname() );
        smartImageView.setImageUrl(SessionManager.getSERWERURL() + "userphoto/" + findFriends.get(position).getId());

        return rowView;

    };

}