package SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.khadokapp.Login;
import com.example.khadokapp.MainActivity;
import com.example.khadokapp.R;
import com.example.khadokapp.ShowCart;

import Dashboard.DashboardActivity;

public class Welcome_Text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_text);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome_Text.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}