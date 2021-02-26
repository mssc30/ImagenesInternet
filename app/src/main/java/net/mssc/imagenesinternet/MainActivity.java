package net.mssc.imagenesinternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Super> listSuper;
    private RecyclerView rvSuper;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSuper = findViewById(R.id.rvSuper);
        listSuper = new ArrayList<Super>();
        listSuper.add(new Super("Spiderman", "https://simplifiedcoding.net//demos//view-flipper//images//spiderman.jpg"));
        listSuper.add(new Super("Thor", "https:\\/\\/simplifiedcoding.net\\/demos\\/view-flipper\\/images\\/thor.jpg"));

        rvSuper.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvSuper.setLayoutManager(layoutManager);
        adapter = new AdaptadorSuper(getApplicationContext(), listSuper);
        rvSuper.setAdapter(adapter);
    }

    class Super{
        private String name;
        private String url;

        public Super(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}