package zespolowe.pl.aplikacja.activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

/**
 *  Aktywność odpowiedzialna za robienie zdjecia, przesłanie go do serwera w celu zczytania
 *  danych przez silnik OCR i przekazanie odebranych danych do następnej aktywności.
 */

public class Camera_Activity extends AppCompatActivity {
    @Bind(R.id.image_camera1) ImageView _imageprev;

    public ImageView imageHolder;
    private final int requestCode = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    SessionManager session;
    User user;
    public File addedImageFile;
    public Long receiptId;

    /**
     *  Implementacja metody onCreate z klasy Activity. Wywoływana jest w momencie tworzenia aktywności.
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        user = session.getUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_okno);

        imageHolder = (ImageView) findViewById(R.id.image_camera1);
        AppCompatButton fab = (AppCompatButton) findViewById(R.id.btn_wyslij_paragon_na_serw);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SendImageToReceiptAPITask task = new SendImageToReceiptAPITask();
                    task.execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        aparat();

        }


    /**
     * Funkcja przekazuje dane pobrane z paragonu do następnej aktywności.
     * @param receipt
     */
    private void wyslij(Receipt receipt) {

        Intent intent = new Intent(Camera_Activity.this, Produkty_Edycja_Activity.class);
        intent.putExtra("receiptId", receipt.getId());
        startActivity(intent);


    }

    /**
     *  Metoda odpowiedzialna za obsługe aparatu
     */
    public void aparat(){
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    /**
     *  Metoda wysyła zdjęcie do serwera i pobiera przetworzone dane
     */

    private class SendImageToReceiptAPITask extends AsyncTask<Void, Integer, Void> {
        User us;
        byte[] img = new byte[0];
        String img_str;
        File addedImage;
        ProgressDialog progressDialog;

        protected Void doInBackground(Void... params) {



            try {

                img = org.apache.commons.io.FileUtils.readFileToByteArray(addedImage);

                img_str = Base64.encodeToString(img, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .readTimeout(30,TimeUnit.SECONDS)
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .build();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SessionManager.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();


            UserService userService = retrofit.create(UserService.class);
            Call<Receipt> call = userService.sendReceiptImagetoAPI(us.getId(), img_str);
            try {
                Receipt receipt = call.execute().body();
                System.out.println("Zdjęcie poprawnie wysłane");
                wyslij(receipt);
            } catch (Exception e) {
                System.out.println("Błąd wysyłania zdjęcia\n");
                e.printStackTrace();
            }

            return null;
        }



        protected void onPreExecute() {
            ImageView image = (ImageView) findViewById(R.id.image_camera1);
            us = user;
            addedImage = addedImageFile;
            progressDialog = new ProgressDialog(Camera_Activity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Wysyłanie zdjęcia...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            progressDialog.hide();
        };
    }


    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "GIMME");


        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        if (type == MEDIA_TYPE_IMAGE){

            this.addedImageFile = new File(mediaStorageDir.getPath()
               + File.separator + "photo_" + timeStamp + ".jpg");

        } else {
            return null;
        }
        return this.addedImageFile;
    }

    /**
     *  Metoda pobiera zrobione zdjęcie z pliku i wyświetla na ekranie aktywności
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
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
            }
        }
    }


}


