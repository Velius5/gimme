package zespolowe.pl.aplikacja.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import zespolowe.pl.aplikacja.model.Respon;
import zespolowe.pl.aplikacja.services.UserService;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
   // private static final int REQUEST_SIGNUP = 0;


    @Bind(R.id.input_email_signup)
    EditText _emailText;
    @Bind(R.id.input_password_signup)
    EditText _passwordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;
    @Bind(R.id.input_password_signup_test)
    EditText _passwordText2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    signup();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }

    public void signup()throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (!validate()) {
            onSignupFailed("");
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Tworzenie konta...");
        progressDialog.show();


        final String email = _emailText.getText().toString();
        final String password = HashGeneratorUtils.generateMD5(_passwordText.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        System.out.println(_passwordText.getText() + " " + _passwordText2.getText());
        if(_passwordText2.getText().toString().equals(_passwordText.getText().toString())) {

            Call<Respon> call = userService.registerUser(email, password);

        call.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(Call<Respon> call, Response<Respon> response) {
                Respon resp = response.body();
//                System.out.println(resp.getValue());

                onSignupSuccess();
            }

            @Override
            public void onFailure(Call<Respon> call, Throwable t) {
                System.out.println("Blad.");
                onSignupFailed("Podane konto juz istnieje.");
            }
        });
        } else {
            onSignupFailed("Hasła różnią się od siebie");
            //System.out.println(_passwordText.getText() + " " + _passwordText2.getText());
        }
////////////////////////////////////////////////////////////////////////////////////////////
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        // onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        Toast.makeText(getBaseContext(), "Zaloguj się na swoje konto.", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        //finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void onSignupFailed(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("podano błędny adres email");
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
        // Disable going back to the MainActivity
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}


