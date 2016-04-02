package zespolowe.pl.aplikacja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpClientExample extends ActionBarActivity {

    TextView textMsg, textPrompt;
//    final String textSource = "https://sites.google.com/site/androidersite/text.txt";
        final String textSource = "https://umk-zespolowka-2016.herokuapp.com/api/users/getall";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpclientexample);

        textPrompt = (TextView)findViewById(R.id.textprompt);
        textMsg = (TextView) findViewById(R.id.textmsg);

        textPrompt.setText("czekaj...");

        new MyTask().execute();
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
                while((stringBuffer = bufferReader.readLine()) != null) {
                    stringText += stringBuffer;
                }

                bufferReader.close();
                textResult = stringText;

            } catch(MalformedURLException e) {
                e.printStackTrace();
                textResult = e.toString();
            } catch(IOException e) {
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            textMsg.setText(textResult);
            textPrompt.setText("koniec!");
            super.onPostExecute(result);
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*

import javax.net.ssl.HttpsURLConnection;

public class HttpClientExample extends AppCompatActivity {


    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        HttpClientExample http = new HttpClientExample();

        System.out.println("Testing 1 - Send Http GET request");
        try {
            http.sendGet();
        } catch (Exception e) {
            e.printStackTrace();System.out.println("Błąd1");
        }

        System.out.println("\nTesting 2 - Send Http POST request");
        try {
            http.sendPost();
        } catch (Exception e) {
            e.printStackTrace();System.out.println("Błąd2");
        }
    }

//        Executors.newSingleThreadExecutor().submit(new Runnable()
//        {
//            @Override
//            public void run() {



    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }


}
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////