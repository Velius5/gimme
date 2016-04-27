package zespolowe.pl.aplikacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.User;

public class User_Profile_Activity extends AppCompatActivity {
    @Bind(R.id.profil_foto) SmartImageView _profil_foto;
    @Bind(R.id.profil_imie) TextView _profil_imie;
    @Bind(R.id.profil_nazwisko) TextView _profil_nazwisko;
    @Bind(R.id.profil_email) TextView _profil_email;
    @Bind(R.id.dodaj_znajomego) Button _dodaj_znajomego;

////////////////////////////////////////////////////////////////////////////////
SessionManager session;
    User user;
    private static int RESULT_LOAD_IMAGE = 1;

    //////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_window);
        System.out.println("User_Profile_Activity");
        ButterKnife.bind(this);
        //////////////////////////////////////////////////////////
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        wczytajDaneDoFormularza();

        //////////////////////////////////////////////////////////

        _dodaj_znajomego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodaj_znajomego_();
            }
        });

        try {
            pobierz();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////
    private void wczytajDaneDoFormularza() {
        _profil_imie.setText(user.getName());
        _profil_nazwisko.setText(user.getSurname());
        _profil_email.setText(user.getEmail());
        System.out.println(user.toString());
        _profil_foto.setImageUrl(SessionManager.getSERWERURL()+"userphoto/"+ user.getId());
//        if(!(img == null) && !img.isEmpty()) {
//
//            byte[] imageAsBytes = Base64.decode(img.getBytes(), RESULT_LOAD_IMAGE);
//            Bitmap bp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//            _profil_foto.setImageBitmap(bp);
//        }
    }
    //////////////////////////////////////////////////////////


    private void dodaj_znajomego_() {
        Intent intent = new Intent(this, Add_Friend_Activity.class);
        startActivity(intent);
    }

    public void pobierz()throws UnsupportedEncodingException, NoSuchAlgorithmException {

     /*   ImageView foto_profil = _profil_foto;
        String imie_profil = _profil_imie.getText().toString();
        String nazwisko_profil = _profil_nazwisko.getText().toString();
        String email_profil = _profil_email.getText().toString();
*/
        //TODO dodaj api(get)

        final ProgressDialog progressDialog = new ProgressDialog(User_Profile_Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Pobieranie danych...");
        progressDialog.show();


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://umk-zespolowka-2016.herokuapp.com/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UserService userService = retrofit.create(UserService.class);
//
//        Call<Respon> call = userService.getUserProfile(imie_profil, nazwisko_profil, email_profil);
//
//
//        call.enqueue(new Callback<Respon>() {
//            @Override
//            public void onResponse(Call<Respon> call, Response<Respon> response) {
//                Respon user = response.body();
//                System.out.println(user);
//                onDownoloadSuccess();
//            }
//
//            @Override
//            public void onFailure(Call<Respon> call, Throwable t) {
//                System.out.println("Blad.");
//                onDownoloadFailed();
//            }
//        });


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progressDialog.dismiss();
                    }
                }, 3000);
    }



    public void onDownoloadSuccess() {
        Toast.makeText(getBaseContext(), "", Toast.LENGTH_LONG).show();
        setResult(RESULT_OK, null);



    }

    public void onDownoloadFailed() {
        Toast.makeText(getBaseContext(), "", Toast.LENGTH_LONG).show();
    }


}