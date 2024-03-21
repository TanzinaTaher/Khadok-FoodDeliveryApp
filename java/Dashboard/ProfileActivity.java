package Dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.khadokapp.R;
import com.example.khadokapp.ShowCart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.about);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        return true;
                    case R.id.home_cart:
                        startActivity(new Intent(getApplicationContext(), ShowCart.class));
                        overridePendingTransition(0,0);


                }

                return false;
            }
        });



    }
}