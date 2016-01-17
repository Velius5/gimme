package zespolowe.pl.aplikacja;

import android.os.Vibrator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.IsolatedContext;
import android.util.EventLogTags;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
//import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    private static final String TAG = "MyActivity";

 /*   private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   /*     mDrawerList = (ListView)findViewById(R.id.navigationList);
          mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
          mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/


        Log.d("zespolowe.pl.aplikacja", "uruchomiono onCreate()");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(cameraListener);
    }

  /*  private void addDrawerItems() {
        String[] osArray = { "aa", "dd", "ff", "gg", "hh" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
//        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }*/
/*
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
*/

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
                Toast.makeText(MainActivity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }


    private static long back_pressed_time;
    private static long PERIOD = 2000;

    @Override
    public void onBackPressed()
    {
        if (back_pressed_time + PERIOD > System.currentTimeMillis())
            super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed_time = System.currentTimeMillis();
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

   @Override
        protected void onDestroy() {
            android.os.Process.killProcess(android.os.Process.myPid());
            super.onDestroy();
            Log.d("zespolowe.pl.aplikacja", "uruchomiono onDestroy()");
        }

   @Override
        public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.menu_main, menu);
          return true;
        }


    //menu w gornym rogu ekranu
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
            /*else
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                Toast.makeText(this, "testtest", Toast.LENGTH_SHORT).show();
                return true;
            }
*/
       return super.onOptionsItemSelected(item);
        }
}
