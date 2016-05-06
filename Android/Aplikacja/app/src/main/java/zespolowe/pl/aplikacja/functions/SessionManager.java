package zespolowe.pl.aplikacja.functions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import zespolowe.pl.aplikacja.LoginActivity;
import zespolowe.pl.aplikacja.model.User;


public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    //URL
    public static String SERWERURL="http://192.168.0.104:8080/";

    public static String APIURL=SERWERURL+"api/";



    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "id";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    public static final String KEY_SURNAME = "surname";

    public static final String KEY_EMAIL = "email";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String id, String name, String surname, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_ID, id);
        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_SURNAME, surname);

        editor.putString(KEY_EMAIL, email);



        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
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
        // return user
        return user;
    }

    /**
     * Set session data
     * */
    public void setUserDetails(String name, String surname){

        // Storing name in pref
        editor.remove(KEY_NAME);
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.remove(KEY_SURNAME);
        editor.putString(KEY_SURNAME, surname);


        // commit changes
        editor.commit();
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
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
