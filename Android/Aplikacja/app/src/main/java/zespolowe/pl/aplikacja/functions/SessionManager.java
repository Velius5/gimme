package zespolowe.pl.aplikacja.functions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import zespolowe.pl.aplikacja.activities.LoginActivity;
import zespolowe.pl.aplikacja.model.User;

/**
 *  Klasa tworzy sesję użytkownika
 */

public class SessionManager {
    SharedPreferences pref;

    Editor editor;

    Context _context;

    public static String SERWERURL="http://10.10.251.12:8080/";

    public static String APIURL=SERWERURL+"api/";



    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidHivePref";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    public static final String KEY_SURNAME = "surname";

    public static final String KEY_EMAIL = "email";


    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String name, String surname, String email){
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);

        editor.putString(KEY_SURNAME, surname);

        editor.putString(KEY_EMAIL, email);



        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }

    }


    public User getUserDetails(){
        User user = new User();

        String id = pref.getString(KEY_ID, null);
        String name = pref.getString(KEY_NAME, null);
        String surname = pref.getString(KEY_SURNAME, null);
        String email = pref.getString(KEY_EMAIL, null);


        user.setId(Long.parseLong(id));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        return user;
    }

    public void setUserDetails(String name, String surname){

        editor.remove(KEY_NAME);
        editor.putString(KEY_NAME, name);

        editor.remove(KEY_SURNAME);
        editor.putString(KEY_SURNAME, surname);


        editor.commit();
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public static String getAPIURL() {
        return APIURL;
    }

    public static String getSERWERURL() {
        return SERWERURL;
    }

}
