package zespolowe.pl.aplikacja;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Splac_Activity extends AppCompatActivity {

    @Bind(R.id.zaplac) Button _zaplac;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splac);
        ButterKnife.bind(this);
        Bundle bundle= getIntent().getExtras();

        Long id = (Long) bundle.get("id");

        System.out.println(id);

        _zaplac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}