package com.example.khadokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {


    TextView Email,Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        Email = findViewById(R.id.emailid1_admin);
        Pass =findViewById(R.id.passid1_admin);

    }


    public void btnClick(View view)
    {
        String email,pass;

        email = Email.getText().toString();
        pass = Pass.getText().toString();


        String currect_mail = "encryptedhacker777@gmail.com";
        String currect_pass = "123456";


        if(email.equals(currect_mail) && pass.equals(currect_pass))
        {
            Intent intent = new Intent(AdminLogin.this,Admin_GotoActivity.class);

            Toast.makeText(AdminLogin.this,"Successfull",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }




}