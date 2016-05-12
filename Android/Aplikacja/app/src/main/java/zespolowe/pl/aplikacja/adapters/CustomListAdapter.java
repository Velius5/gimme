package zespolowe.pl.aplikacja.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.activities.Friend_Activity;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.services.UserService;

/**
 * Adapter odpowiedzialny za wygląd oraz działanie listy znajomych
 */

public class CustomListAdapter extends ArrayAdapter {
    private Activity context;
    private List<Friend> friends = new ArrayList<>();

    public CustomListAdapter(Activity context, List<Friend> friends) {
        super(context, R.layout.my_friend_list, friends);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.friends = friends;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_friend_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        SmartImageView imageView = (SmartImageView) rowView.findViewById(R.id.icon);
        TextView balanceTitle = (TextView) rowView.findViewById(R.id.saldo);

        txtTitle.setText(friends.get(position).getName() + " " + friends.get(position).getSurname());
        balanceTitle.setText(String.valueOf(friends.get(position).getBilans() + " zł"));

        ImageButton imgBtn = (ImageButton) rowView.findViewById(R.id.removeFriendBtn);

        imgBtn.setTag(position);
        imgBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          final Long friendId = friends.get((int) v.getTag()).getId();
                                          final Long userId = new SessionManager(context).getUserDetails().getId();

                                          int temp = friends.get(position).getBilans().compareTo(BigDecimal.ZERO);
                                          boolean paid = temp >= 0 ? true : false;
                                          if (paid) {
                                              final AlertDialog alertDialog = new AlertDialog.Builder(context)
                                                      .setTitle("Usuń znajomego")
                                                      .setMessage("Czy napewno chcesz usunąć tego użytkownika z listy znajomych?")
                                                      .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                                          @Override
                                                          public void onClick(DialogInterface dialog, int which) {
                                                              new AsyncTask<Void, Void, Void>() {

                                                                  @Override
                                                                  protected Void doInBackground(Void... params) {
                                                                      System.out.println("Start");
                                                                      Retrofit retrofit = new Retrofit.Builder()
                                                                              .baseUrl(SessionManager.getAPIURL())
                                                                              .addConverterFactory(GsonConverterFactory.create())
                                                                              .build();
                                                                      UserService userService = retrofit.create(UserService.class);
                                                                      Call<Respon> call = userService.removeFriend(userId, friendId);

                                                                      Response<Respon> response = null;
                                                                      try {
                                                                          response = call.execute();
                                                                      } catch (Exception e) {
                                                                          e.printStackTrace();
                                                                      }
                                                                      Respon deleted = response.body();

                                                                      return null;
                                                                  }

                                                                  @Override
                                                                  protected void onPreExecute() {
                                                                      super.onPreExecute();
                                                                      Intent intent = new Intent(context, Friend_Activity.class);
                                                                      context.startActivity(intent);
                                                                  }
                                                              }.execute();
                                                          }
                                                      })
                                                      .setNegativeButton("Nie", null)
                                                      .setInverseBackgroundForced(true)
                                                      .create();
                                              alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                                  @Override
                                                  public void onShow(DialogInterface dialog) {
                                                      alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE);
                                                      alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
                                                  }
                                              });

                                              alertDialog.show();

                                          } else {
                                              final AlertDialog alertDialog = new AlertDialog.Builder(context)
                                                      .setTitle("Usuń znajomego")
                                                      .setMessage("Musisz uregulować długi zanim usuniesz znajomego!")
                                                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                          @Override
                                                          public void onClick(DialogInterface dialog, int which) {
                                                              Intent intent = new Intent(context, Friend_Activity.class);
                                                              context.startActivity(intent);
                                                          }
                                                      })
                                                      .create();

                                              alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                                  @Override
                                                  public void onShow(DialogInterface dialog) {
                                                      alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE);
                                                  }
                                              });
                                              alertDialog.setCancelable(true);
                                              alertDialog.show();


                                          }
                                      }
                                  }
        );

        if (friends.get(position).getBilans().signum() > 0) {
            balanceTitle.setTextColor(Color.GREEN);
        }
        if (friends.get(position).getBilans().signum() < 0) {
            balanceTitle.setTextColor(Color.RED);
        }

        imageView.setImageUrl(SessionManager.getSERWERURL() + "userphoto/"+ friends.get(position).getId());
        return rowView;

    };

}