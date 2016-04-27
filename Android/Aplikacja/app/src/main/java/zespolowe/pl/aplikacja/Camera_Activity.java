package zespolowe.pl.aplikacja;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.functions.ImageManager;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.Receipt;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;


//TODO: dodaj wysyłąnie w api


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
                    sendImageToReceiptApi();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        aparat();

        }


    private void wyslij() {

        final ProgressDialog progressDialog = new ProgressDialog(Camera_Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Pobieranie danych...");
        progressDialog.show();

        Intent intent = new Intent(Camera_Activity.this, Produkty_Edycja_Activity.class);
        intent.putExtra("receiptId", receiptId);
        startActivity(intent);


    }

    public void aparat(){
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void sendImageToReceiptApi() throws IOException {
        ImageView image = (ImageView) findViewById(R.id.image_camera1);
        String img_str;
        if(ImageManager.hasImage(image)) {
            image.buildDrawingCache();
            Bitmap bitmap = image.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //byte[] img = stream.toByteArray();
            byte[] img = org.apache.commons.io.FileUtils.readFileToByteArray(addedImageFile);
            img_str = Base64.encodeToString(img, 0);
        } else {
            img_str = "BRAK";
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SessionManager.getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<Receipt> call = userService.sendReceiptImagetoAPI(user.getId(), img_str);
        call.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {
                Receipt resp = response.body();
                if (resp != null) {
                    //System.out.println(resp.toString());
                    receiptId = resp.getId();
                    //onAddSuccess();
                    wyslij();
                }
            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable t) {
                System.out.println("Blad.");
                //onAddFailed("Zmiana nie powiodła się");
            }
        });


    }

    /** Create a file Uri for saving an image or video */
    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "GIMME");

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "GIMME");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        if (type == MEDIA_TYPE_IMAGE){

            this.addedImageFile = new File(mediaStorageDir.getPath()
               + File.separator + "photo_" + timeStamp + ".jpg");

//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }
        return this.addedImageFile;
    }

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


