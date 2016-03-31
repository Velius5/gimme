package zespolowe.pl.aplikacja;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Camera_Activity extends AppCompatActivity{


         private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
         private Uri fileUri;
         private static final String TAG = "Camera_Activity";
         Context context;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.cemera_okno);

            context = getApplicationContext();

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(cameraListener);
            };


    private View.OnClickListener cameraListener = new View.OnClickListener() {
        public void onClick(View view) {
            takePhoto(view);

        }
    };




    private void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+
                File.separator + "IMG" + timeStamp + ".jpg");
        fileUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }


    @Override
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
                Toast.makeText(Camera_Activity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

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
  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

   /* @Override
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
    }*/
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
