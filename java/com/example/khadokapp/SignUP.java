package com.example.khadokapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignUP extends AppCompatActivity {
    EditText emailid,passid,fullnameid,usernameid;
    RadioButton malegenderid,femalegenderid;
    Button signupbtn;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("SignUp");

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.emailid);
        passid = findViewById(R.id.passid);
        fullnameid = findViewById(R.id.fullname);
        usernameid = findViewById(R.id.username);
        malegenderid = (RadioButton)findViewById(R.id.radio_male);
        femalegenderid = findViewById(R.id.radio_female);

        signupbtn = findViewById(R.id.register);

        databaseReference = FirebaseDatabase.getInstance().getReference("SignUpRealtime");
    }
    public void RealtimeClick(View view)
    {
        String fullname = fullnameid.getText().toString();
        String username = usernameid.getText().toString();
        String email = emailid.getText().toString();
        String pass = passid.getText().toString();


        if(malegenderid.isChecked())
        {
            gender  = "Male";
        }
        if(femalegenderid.isChecked())
        {
            gender = "female";
        }

        if(email.isEmpty())
        {
            emailid.setError("Please enter");
            emailid.requestFocus();
        }
        if(pass.isEmpty())
        {
            passid.setError("Please enter");
            passid.requestFocus();
        }
        if(fullname.isEmpty())
        {
            passid.setError("Please enter");
            passid.requestFocus();
        }
        if(username.isEmpty())
        {
            passid.setError("Please enter");
            passid.requestFocus();
        }

        mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignUP.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    SignUpRealtime info = new SignUpRealtime(fullname,username,email,gender,pass);
                    FirebaseDatabase.getInstance().getReference("SignUpRealtime").child(FirebaseAuth.
                            getInstance().getCurrentUser().getUid()).
                            setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SignUP.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUP.this,PostListActivity.class);
                            //Common.currentUser = info;
                            startActivity(intent);
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignUP.this, "Signup Unsuccessfull,please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void btnclick(View view)
    {
        String email = emailid.getText().toString();
        String pass = passid.getText().toString();

        if(email.isEmpty())
        {
            emailid.setError("Please enter email");
            emailid.requestFocus();
        }
        else if(pass.isEmpty())
        {
            passid.setError("Please enter your password");
            passid.requestFocus();
        }
        else if(email.isEmpty() && pass.isEmpty())
        {
            Toast.makeText(SignUP.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(!(email.isEmpty() && pass.isEmpty()))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignUP.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent intent = new Intent(SignUP.this,PostListActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(SignUP.this, "Signup Unsuccessfull,please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(SignUP.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}