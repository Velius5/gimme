package zespolowe.pl.aplikacja;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import android.app.Activity;
/*import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;*/


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    /*HttpParams myHttpParams=new BasicHttpParams();
    myHttpParams.setParameter("id", "173217639");
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet("foo.com/iot/developers/apps.json");
    request.setParams(myHttpParams);
    request.addHeader("Authorization", "Basic myToken=");
    HttpResponse response = client.execute(request);*/

   /* private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    private static final String TAG = "MyActivity";*/
    Button zaloguj_sie;
    Button zarejestuj_sie;
  /*  Button logowanie;
    Button rejestracja;*/
    /*ImageButton ikona_manu1;
    ImageButton ikona_manu2;
    ImageButton ikona_manu3;
    ImageButton ikona_manu4;
    ImageButton ikona_manu5;
    ImageButton ikona_manu6;*/
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        zaloguj_sie = (Button) findViewById(R.id.button3);
        zarejestuj_sie = (Button) findViewById(R.id.button4);

       /* rejestracja = (Button) findViewById(R.id.button);
        logowanie = (Button) findViewById(R.id.button2);*/
/*
        ikona_manu1 = (ImageButton) findViewById(R.id.imageButton);*/
       /* ikona_manu2 = (ImageButton) findViewById(R.id.imageButton2);
        ikona_manu3 = (ImageButton) findViewById(R.id.imageButton3);
        ikona_manu4 = (ImageButton) findViewById(R.id.imageButton4);
        ikona_manu5 = (ImageButton) findViewById(R.id.imageButton5);
        ikona_manu6 = (ImageButton) findViewById(R.id.imageButton6);*/

        zaloguj_sie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // setContentView(R.layout.logintest);
                setContentView(R.layout.login);




            }
        });


        zarejestuj_sie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.register);
            }
        });
/*
        rejestracja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });

       logowanie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });

        ikona_manu1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });

        ikona_manu2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.galeria_menu);
            }
        });

        ikona_manu3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_ustawienia);
            }
        });

        ikona_manu4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });

        ikona_manu5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });

        ikona_manu6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.menu_karta);
            }
        });*/



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(cameraListener);*/


    }


/*
    private View.OnClickListener cameraListener = new View.OnClickListener() {
        public void onClick(View view) {
            takePhoto(view);

        }
    };
*/

/*

    private void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+
                File.separator + "IMG" + timeStamp + ".jpg");
        fileUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }*/


/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            Uri selectedImage = fileUri;
            getContentResolver().notifyChange(selectedImage, null);
            ImageView imageView = (ImageView) findViewById(R.id.image_camera1);

            ContentResolver cr = getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                imageView.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }*/


 /*   private static long back_pressed_time;
    private static long PERIOD = 2000;

   @Override
    public void onBackPressed()
    {
        if (back_pressed_time + PERIOD > System.currentTimeMillis())
            super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed_time = System.currentTimeMillis();
    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onPause()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onStop()");
    }
/*
    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
        Log.d("zespolowe.pl.aplikacja", "uruchomiono onDestroy()");
    }*/
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

*/

  /*  //menu w gornym rogu ekranu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // zareaguj na podstawie ID itemu
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //  return true;
            Toast.makeText(this, "wybrano: Ustawienia", Toast.LENGTH_SHORT).show();
        }else
        if (id == R.id.action_add){
            Toast.makeText(this, "wybrano: Dodaj coś", Toast.LENGTH_SHORT).show();
        }else
        if (id == R.id.about){
            Toast.makeText(this, "wybrano: info", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }*/
}
