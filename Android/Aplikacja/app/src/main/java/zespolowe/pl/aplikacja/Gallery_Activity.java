package zespolowe.pl.aplikacja;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Gallery_Activity extends AppCompatActivity {

    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data = new ArrayList<>();

    public static String IMGS[] = {
            "https://i.ytimg.com/vi/OXt8qa-m7-M/maxresdefault.jpg",
            "https://i.ytimg.com/vi/otya_BGfMwo/maxresdefault.jpg",
            "https://images.unsplash.com/photo-1441155472722-d17942a2b76a?q=80&fm=jpg&w=1080&fit=max&s=80cb5dbcf01265bb81c5e8380e4f5cc1",
            "https://images2.alphacoders.com/856/85642.jpg",
            "https://images.unsplash.com/photo-1434873740857-1bc5653afda8?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcROXuGQJ_L9K-p3jjiU3DMsQTllDZ4yR0n4mYjypDWRiebQA5eV",
            "https://i.ytimg.com/vi/weIiQfNVFg0/hqdefault.jpg",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSfiqpbERwnWKReISLh4mBBhOIyU82u481trOr0c0fy4Jzp_jpl",
            "http://wallpaperlayer.com/img/2015/6/lovely-view-wallpaper-hd-8760-9088-hd-wallpapers.jpg",
            "https://lh6.ggpht.com/fcG5IwCxwFJ5R5_VpAw9bNcmtLJs0PiXicHuiXkpovAG3SKMjmmwScKae82q8GKPxGw=h900",
            "http://eskipaper.com/images/perfect-view-wallpaper-2.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);

        for (int i = 1; i < IMGS.length; i++) {

            ImageModel imageModel = new ImageModel();
//            imageModel.setName("Paragon " + i);
            imageModel.setUrl(IMGS[i]);
            data.add(imageModel);

        }

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
        setSupportActionBar(toolbar);
        else System.out.println(toolbar.toString());*/

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new GalleryAdapter(Gallery_Activity.this, data);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
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

}






