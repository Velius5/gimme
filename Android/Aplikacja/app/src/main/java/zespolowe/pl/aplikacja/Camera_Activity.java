package zespolowe.pl.aplikacja;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO:po wyswietleniu zdjecia na imageCamera1 dodaj opcje(w formie przycisku) "czy wysłac na serwer?"
//TODO: Nie zaomnij o zminie w pliku camera_okno.xml



public class Camera_Activity extends AppCompatActivity {

    private ImageView imageHolder;
    private final int requestCode = 20;
    public final static String EXTRA_MESSAGE = "zespolowe.pl.aplikacja.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_okno);

        imageHolder = (ImageView)findViewById(R.id.image_camera1);
        AppCompatButton fab = (AppCompatButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

//        Button capturedImageButton = (Button)findViewById(R.id.photo_button);
//        capturedImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);




            // display the image from SD Card to ImageView Control
            String storeFilename = "photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            imageHolder.setImageBitmap(mBitmap);
        }
    }

    public void showGreetings(View view)
    {

        String button_text;
        button_text = ((Button) view) .getText().toString();
        if(button_text.equals("Info"))
        {

            Intent intent = new Intent(this, Camera_Activity.class);
            startActivity(intent);
        }
        else if (button_text.equals("Info"))
        {
            Intent intent = new Intent(this, Camera_Activity.class);
            startActivity(intent);
        }
    }
    public void saveImage(Context context, Bitmap b,String name,String extension){
        name=name+"."+extension;
        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }


    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate){
        //To change external into internal change
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
//        into
//        File outputFile = new File(context.getFilesDir(), "photo_" + currentDate + ".jpg");
//
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImageFileFromSDCard(String filename){
        Bitmap bitmap = null;
        File imageFile = new File(Environment.getExternalStorageDirectory() + filename);
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

 /*   @Override
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
    }*/
}
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.AppCompatButton;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//public class Camera_Activity extends AppCompatActivity {
//    private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
//    private Uri fileUri;
//    private static final String TAG = "Camera_Activity";
//    Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("Camera_Activity_onCreate");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.camera_okno);
//        context = getApplicationContext();
//        AppCompatButton fab = (AppCompatButton) findViewById(R.id.fab);
//        fab.setOnClickListener(cameraListener);
//    }
//
//    private View.OnClickListener cameraListener = new View.OnClickListener() {
//        public void onClick(View view) {
//            System.out.println("Camera_Activity_fab");
//
//            takePhoto(view);
//
//        }
//    };
//
//    private void takePhoto(View view) {
//        System.out.println("Camera_Activity_takePhoto");
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                + File.separator + "IMG" + timeStamp + ".jpg");
//        fileUri = Uri.fromFile(photo);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        System.out.println("Camera_Activity_onActivityResult");
//
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        if (resultCode == RESULT_OK) {
//            System.out.println("Camera_Activity_RESULT_OK");
//
//            Uri selectedImage = fileUri;
//            getContentResolver().notifyChange(selectedImage, null);
//            ImageView imageView = (ImageView) findViewById(R.id.image_camera1);
//
//            ContentResolver cr = getContentResolver();
//            Bitmap bitmap;
//            try {
//                System.out.println("Camera_Activity_getBitmap");
//
//                bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
//                imageView.setImageBitmap(bitmap);
//                Toast.makeText(Camera_Activity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
//            } catch (Exception e) {
//                Log.e(TAG, e.toString());
//                System.out.println("Camera_Activity_catch po bitmap");
//
//            }
//        }
//        System.out.println("Camera_Activity_KONIEC_onActivityResult");
//
//    }
//
//}
