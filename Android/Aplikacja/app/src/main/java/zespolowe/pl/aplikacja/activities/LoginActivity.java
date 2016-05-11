package zespolowe.pl.aplikacja.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.HashGeneratorUtils;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.NotificationsService;
import zespolowe.pl.aplikacja.services.UserService;

/**
 *  Aktywność odpowiedzialna za obsługe logowania użytkownika
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    SessionManager session;
    User user;
    GcmNetworkManager mGcmNetworkManager;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mGcmNetworkManager = GcmNetworkManager.getInstance(this);
        session = new SessionManager(getApplicationContext());
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                System.out.println("LoginActivity_on click");
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }



    /**
     *  Metoda odpowiedzialna za logowanie użytkownika
     */
    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Log.d(TAG, "Loginee");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Weryfikacja...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = HashGeneratorUtils.generateMD5(_passwordText.getText().toString());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.loginUser(email, password);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User us = response.body();
                    user = us;
                    runSessionManager();

                    onLoginSuccess();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("Blad.");
                    onLoginFailed();
                }
            });



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    /**
     *  Metoda odpowiedzialna za uruchomienie nowej sesji
     */
    private void runSessionManager() {
        new Thread() {
            @Override
            public void run() {

                session.createLoginSession(String.valueOf(user.getId()),user.getName(),user.getSurname(),user.getEmail());
                System.out.println(session.isLoggedIn());
            }
        }.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }



    /**
     *  Metoda wykonująca się po poprawnym zalogowaniu użytkownika do systemu
     *  Uruchamia aktywność głównego menu aplikacji
     */
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        setNotificationsService();
        Intent intent = new Intent(this, Menu_Activity.class);
        startActivity(intent);
    }

    /**
     *  Metoda wykonująca się po nie prawidłowym zalogowaniu użytkownika
     *  Wyświetla kominukat o nie udanej próbie logowania
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Logowanie nie powiodło się", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public void setNotificationsService() {
        String userId = String.valueOf(user.getId());
        PeriodicTask task = new PeriodicTask.Builder()
                .setService(NotificationsService.class)
                .setTag(userId)
                .setPeriod(5L)
                .setFlex(3L)
                .setPersisted(true)
                .setRequiredNetwork(com.google.android.gms.gcm.Task.NETWORK_STATE_ANY)
                .build();

        mGcmNetworkManager.schedule(task);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("wpisz poprawny adres email");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("wprowadź od 4 do 10 znaków");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}




