package com.example.khadokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin_GotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_goto);
    }

    public void datashow(View view)
    {
        Intent intent = new Intent(Admin_GotoActivity.this,Admin_PostActivity.class);
        startActivity(intent);
    }


    public void foodshow(View view)
    {
        Intent intent = new Intent(Admin_GotoActivity.this,Admin_FirstActivity.class);
        startActivity(intent);
    }
    public void currentOrdershow(View view)
    {
        Intent intent = new Intent(Admin_GotoActivity.this,Admin_CurrentOrder_Show.class);
        startActivity(intent);
    }
}