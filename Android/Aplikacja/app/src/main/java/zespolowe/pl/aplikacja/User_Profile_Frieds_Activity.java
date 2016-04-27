/*
package zespolowe.pl.aplikacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class User_Profile_Frieds_Activity extends AppCompatActivity {

    @Bind(R.id.profil_Friends_foto) ImageView _profil_Friends_foto;
    @Bind(R.id.profil_Friends_imie) TextView _profil_Friends_imie;
    @Bind(R.id.profil_Friends_nazwisko) TextView _profil_Friends_nazwisko;
    @Bind(R.id.saldo) TextView _saldo;
    @Bind(R.id.splac_dlug) Button _splac_dlug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_friends_window);
        System.out.println("User_Profile_Frieds_Activity");
        ButterKnife.bind(this);
        Bundle bundle= getIntent().getExtras();

        String saldo = bundle.getString("saldo");
        Long id = (Long) bundle.get("id");
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        System.out.println(id);
        _saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        _splac_dlug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splac_dlug();
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

    private void splac_dlug() {
        Intent intent = new Intent(this, Splac_Activity.class);
        startActivity(intent);
    }


    public void pobierz()throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ImageView foto_profil_Friends = _profil_Friends_foto;
        String imie_profil_Friends = _profil_Friends_imie.getText().toString();
        String nazwisko_profil_Friends = _profil_Friends_nazwisko.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(User_Profile_Frieds_Activity.this,
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
//        Call<Respon> call = userService.getUserProfile(imie_profil_Friends, nazwisko_profil_Friends, email_profil_Friends);
//
//        call.enqueue(new Callback<Respon>() {
//            @Override
//            public void onResponse(Call<Respon> call, Response<Respon> response) {
//                Respon user = response.body();
//                System.out.println(user.toString());
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
        */
/*Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();*//*

        setResult(RESULT_OK, null);


    }

    public void onDownoloadFailed() {
        */
/*Toast.makeText(getBaseContext(), "dupa", Toast.LENGTH_LONG).show();*//*

    }


}*/
