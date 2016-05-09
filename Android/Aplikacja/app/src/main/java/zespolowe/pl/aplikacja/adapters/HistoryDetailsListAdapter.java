package zespolowe.pl.aplikacja.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.User;

/**
 * Created by Piotr on 2016-05-07.
 */
public class HistoryDetailsListAdapter extends BaseAdapter {

    private List<User> userList;
    private int firstTypeDivider;
    private Activity context;

    public HistoryDetailsListAdapter(Activity context, List<User> userList, int count) {
        this.userList = userList;
        this.firstTypeDivider = count;
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(firstTypeDivider == -1) return 2;
        return position >= firstTypeDivider ? 1 : 0;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.history_details_item, null, true);

        TextView userText = (TextView) rowView.findViewById(R.id.historyUser);
        User user = userList.get(position);

        String str = user.getName() + " " + user.getSurname();
        userText.setText(str);
        switch (getItemViewType(position)){
            case 0 : userText.setTextColor(Color.GREEN);
                break;
            case 1 : userText.setTextColor(Color.RED);
                break;
            case 2 :
                break;
        }

        return rowView;
    }
}
