package zespolowe.pl.aplikacja.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zespolowe.pl.aplikacja.adapters.GalleryAdapter;
import zespolowe.pl.aplikacja.functions.SessionManager;
import zespolowe.pl.aplikacja.model.HistoryProduct;
import zespolowe.pl.aplikacja.model.ImageModel;
import zespolowe.pl.aplikacja.R;
import zespolowe.pl.aplikacja.listeners.RecyclerItemClickListener;
import zespolowe.pl.aplikacja.model.User;
import zespolowe.pl.aplikacja.services.UserService;

public class Gallery_Activity extends AppCompatActivity {

    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data;
    List<Long> recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);

        data = new ArrayList<>();
        recList = new ArrayList<>();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                User user = new SessionManager(getApplicationContext()).getUserDetails();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SessionManager.getAPIURL())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = retrofit.create(UserService.class);
                Call<List<Long>> call = userService.getAllReceiptsIdList(user.getId());
                try{
                    Response<List<Long>> response = call.execute();
                    recList = response.body();
                }catch (IOException e){
                    System.out.println(e);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for(Long id : recList){
                    ImageModel imageModel = new ImageModel();
                    imageModel.setUrl(SessionManager.getSERWERURL()+"receiptphoto/"+ id);
                    data.add(imageModel);
                }
                mRecyclerView = (RecyclerView) findViewById(R.id.list);
                mRecyclerView.setLayoutManager(new GridLayoutManager(Gallery_Activity.this, 3));
                mRecyclerView.setHasFixedSize(true);


                mAdapter = new GalleryAdapter(Gallery_Activity.this, data);
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Gallery_Activity.this,
                        new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                Intent intent = new Intent(Gallery_Activity.this, DetailActivity.class);
                                intent.putParcelableArrayListExtra("data", data);
                                intent.putExtra("pos", position);
                                startActivity(intent);

                            }
                        }));

            }
        }.execute();
    }

}






