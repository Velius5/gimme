package zespolowe.pl.aplikacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.HashGeneratorUtils;
import zespolowe.pl.aplikacja.functions.ImageManager;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

/**
 * Created by Rafał on 2016-04-17.
 */
public class Ustawienia extends AppCompatActivity {
    @Bind(R.id.dodaj_imie_ust) EditText _dodaj_imie_ust;
    @Bind(R.id.dodaj_nazwisko_ust) EditText _dodaj_nazwisko_ust;
    @Bind(R.id.btn_Ustawiena_zapisz) Button _btn_Ustawiena_zapisz;
    @Bind(R.id.btn_Ustawiena_Wyloguj) Button _btn_Ustawiena_Wyloguj;
    @Bind(R.id.link_licencje) TextView _link_licencje;
    @Bind(R.id.link_o_nas) TextView _link_o_nas;
    @Bind(R.id.input_password_ust) EditText _password_ust_Text;
    @Bind(R.id.input_password_ust_repeat) EditText _password_ust_repeat_Text;

    @Bind(R.id.cpic) Button _dodaj_foto;
    @Bind(R.id.Imageprev) SmartImageView _imageprev;

    SessionManager session;
    User user;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ustawienia);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        wczytajDaneDoFormularza();


        _dodaj_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FromCard();
            }
        });

        _btn_Ustawiena_zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    zapisz();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        _btn_Ustawiena_Wyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyloguj();
            }
        });

        _link_licencje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: dodaj link do licencii
            }
        });

        _link_o_nas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: dodaj link do About as
            }
        });
    }



    private void wczytajDaneDoFormularza() {
        _dodaj_imie_ust.setText(user.getName());
        _dodaj_nazwisko_ust.setText(user.getSurname());
        _imageprev.setImageUrl(SessionManager.getSERWERURL() + "userphoto/" + user.getId());
        System.out.println(user.toString());
    }

    public void zapisz() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        _btn_Ustawiena_zapisz.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Ustawienia.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Zapisywanie...");
        progressDialog.show();


        final ImageView image = _imageprev;
        String img_str;
        if(ImageManager.hasImage(image)) {
            image.buildDrawingCache();
            Bitmap bitmap = image.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] img = stream.toByteArray();
            img_str = Base64.encodeToString(img, 0);
        } else {
            img_str = "BRAK";
        }


        final String name = _dodaj_imie_ust.getText().toString();
        final String surname = _dodaj_nazwisko_ust.getText().toString();
        String password = HashGeneratorUtils.generateMD5(_password_ust_Text.getText().toString());


        if(_password_ust_Text.getText().toString().isEmpty()) {
            password = "BRAK";
            sendRequest(name, surname, img_str, user.getEmail(), password);
        } else {
            if (_password_ust_Text.getText().toString().equals(_password_ust_repeat_Text.getText().toString())) {
                sendRequest(name, surname, img_str, user.getEmail(), password);
            } else {
                onAddFailed("Hasła różnią się od siebie");
            }
        }

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void sendRequest(String name, String surname, String image, String email, final String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<Respon> call = userService.editProfile(user.getId(), name, surname, image, email, password);
        call.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(Call<Respon> call, Response<Respon> response) {
                Respon resp = response.body();
                System.out.println(resp);
                onAddSuccess();
            }

            @Override
            public void onFailure(Call<Respon> call, Throwable t) {
                System.out.println("Blad.");
                onAddFailed("Zmiana nie powiodła się");
            }
        });
    }

    public void onAddSuccess() {
        _btn_Ustawiena_zapisz.setEnabled(true);
        setResult(RESULT_OK, null);
        /*ImageView image = _imageprev;
        String img_str = null;
        if(ImageManager.hasImage(image)) {
            image.buildDrawingCache();
            Bitmap bitmap = image.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] img = stream.toByteArray();
            img_str = Base64.encodeToString(img, 0);
        }*/
        session.setUserDetails(
                _dodaj_imie_ust.getText().toString(),
                _dodaj_nazwisko_ust.getText().toString()
                );
        Toast.makeText(getBaseContext(), "Zmiana powiodła się", Toast.LENGTH_LONG).show();
    }

    public void onAddFailed(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
        _btn_Ustawiena_zapisz.setEnabled(true);
    }

    public void wyloguj() {
        Intent intent = new Intent(this, Log_Rej_Activity.class);
        if(Build.VERSION.SDK_INT >= 11) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(intent);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static int RESULT_LOAD_IMAGE = 1;
    public void FromCard() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.Imageprev);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

}
