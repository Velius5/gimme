package zespolowe.pl.aplikacja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class User_Profile_Activity extends AppCompatActivity {

    TextView nazwisko_profil, telefon_profil;
    final String textSource = "https://192.168.0.104/api/user/1";//user/id/getfriends
//    final String textSource2 = "";


    //pobieranie foto
    ArrayList<ImageModel> data = new ArrayList<>();
    public static String IMGS[] = {
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_window);
        System.out.println("User_Profile_Activity");

        //pobieranie danych
        nazwisko_profil = (TextView) findViewById(R.id.profil_imie_nazw);
        telefon_profil = (TextView) findViewById(R.id.profil_telefon);
        new MyTask().execute();

      /*  //pobieranie zdjecia
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(IMGS[i]);
        data.add(imageModel);*/

    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {
            URL textUrl;

            try {
                textUrl = new URL(textSource);

                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(textUrl.openStream()));

                String stringBuffer;
                String stringText = "";
                while ((stringBuffer = bufferReader.readLine()) != null) {
                    stringText += stringBuffer;
                }

                bufferReader.close();
                textResult = stringText;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                textResult = e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            nazwisko_profil.setText(textResult);
//        telefon_profil.setText(textResult);
            super.onPostExecute(result);
        }
    }

}