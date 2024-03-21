package Dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.khadokapp.MainActivity;
import com.example.khadokapp.PlaceOrder;
import com.example.khadokapp.PostListActivity;
import com.example.khadokapp.R;
import com.example.khadokapp.ShowCart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import adapter.AsiaFoodAdapter;
import adapter.PopularFoodAdapter;
import model.AsiaFood;
import model.PopularFood;


public class DashboardActivity extends AppCompatActivity {


    RecyclerView popularRecycler,asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;

    Button gotoMenuListBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("HomePage");



        List<PopularFood> popularFoodList = new ArrayList<>();


        popularFoodList.add(new PopularFood("Pizza","TK 550",R.drawable.pizza2));
        popularFoodList.add(new PopularFood("Biriyani","TK 290",R.drawable.burger1));
        popularFoodList.add(new PopularFood("Burger","TK 350",R.drawable.kabab));
        popularFoodList.add(new PopularFood("Cake","TK 200",R.drawable.sub_sandwich));
        popularFoodList.add(new PopularFood("Cake","TK 210",R.drawable.shawrma));
        popularFoodList.add(new PopularFood("Cake","TK 190",R.drawable.fried_chicken));
        popularFoodList.add(new PopularFood("Cake","TK 990",R.drawable.cake1));

        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();

        asiaFoodList.add(new AsiaFood("Samusa","TK 130",R.drawable.samusa,"4.5","Ghar"));
        asiaFoodList.add(new AsiaFood("Pitha","TK 150",R.drawable.pitha,"4.6","Pitha_Rajotto"));
        asiaFoodList.add(new AsiaFood("Doi Bora","TK 160",R.drawable.doi_bora,"4.77","Urmi's Speacial"));
        asiaFoodList.add(new AsiaFood("Cake","TK 850",R.drawable.cake,"4.2","Shumis Kitchen"));
        asiaFoodList.add(new AsiaFood("Cake","TK 850",R.drawable.pitha_2,"4.2","Pitha_Wala"));


        setAsiaRecycler(asiaFoodList);

        gotoMenuListBtn = findViewById(R.id.button);








        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), PlaceOrder.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
                        return true;
                    case R.id.home_cart:
                        startActivity(new Intent(getApplicationContext(), ShowCart.class));
                        overridePendingTransition(0,0);


                }

                return false;
            }
        });

    }


    public void gotoMenu(View view)
    {
        Intent intent = new Intent(DashboardActivity.this, PostListActivity.class);
        startActivity(intent);

    }

    public void setPopularRecycler(List<PopularFood> popularFoodList)
    {
        popularRecycler = findViewById(R.id.popular_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this,popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);
    }

    public void setAsiaRecycler(List<AsiaFood>AsiaFoodList)
    {
        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this,AsiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);
    }


}