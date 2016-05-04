package zespolowe.pl.aplikacja;

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

SessionManager session;
    User user;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_window);
        System.out.println("User_Profile_Activity");
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        wczytajDaneDoFormularza();

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

    private void wczytajDaneDoFormularza() {
        _profil_imie.setText(user.getName());
        _profil_nazwisko.setText(user.getSurname());
        _profil_email.setText(user.getEmail());
        System.out.println(user.toString());
        _profil_foto.setImageUrl(SessionManager.getSERWERURL()+"userphoto/"+ user.getId());
    }


    private void dodaj_znajomego_() {
        if(user.getName() != null) {
            Intent intent = new Intent(this, Add_Friend_Activity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Zanim dodasz znajomych musisz uzupełnić swój profil", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void pobierz()throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //TODO dodaj api(get)

     /*   final ProgressDialog progressDialog = new ProgressDialog(User_Profile_Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Pobieranie danych...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }


}