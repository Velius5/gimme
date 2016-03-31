package zespolowe.pl.aplikacja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Menu_Activity extends AppCompatActivity {
    private static final String TAG = "Menu_Activity";

    @Bind(R.id.imageButton)
    ImageButton camera;

    @Bind(R.id.imageButton2)
    ImageButton gallery;

    @Bind(R.id.imageButton3)
    ImageButton datail;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_karta);
        ButterKnife.bind(this);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera_();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery_();
            }
        });

        datail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_();
            }
        });
    }

    private void camera_() {
        Intent intent = new Intent(this, Camera_Activity.class);
        startActivity(intent);
    }

    private void gallery_() {
        Intent intent = new Intent(this, Gallery_Activity.class);
        startActivity(intent);
    }

    private void detail_() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
