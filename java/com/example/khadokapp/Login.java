package com.example.khadokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Dashboard.DashboardActivity;


public class Login extends AppCompatActivity {
    EditText emailid,passid;
    Button loginbtn;
    FirebaseAuth mFirebaseAuth;
    TextView forgotpass;


    public static String User_name = "";

    // private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("");

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.emailid1);
        passid = findViewById(R.id.passid1);
        loginbtn = (Button)findViewById(R.id.login);
        forgotpass = findViewById(R.id.forgotpass);

    }

    public void AdminLogIn(View view)
    {
        Intent intent = new Intent(Login.this,AdminLogin.class);
        startActivity(intent);

    }


    public void Loginbtn(View view)
    {
        String email = emailid.getText().toString();
        String pass = passid.getText().toString();

        if(email.isEmpty())
        {
            emailid.setError("Email is required");
            emailid.requestFocus();
        }
       if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailid.setError("Please enter valid email adress");
            emailid.requestFocus();
        }

        if(pass.isEmpty())
        {
            passid.setError("Password is required");
            passid.requestFocus();
        }

       if(pass.length()<6)
        {
            passid.setError("Min pass len is 6");
            passid.requestFocus();
        }
        mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        SignUpRealtime info  = new SignUpRealtime(email,pass);
                        User_name = email;
                        Intent i = new Intent(Login.this, DashboardActivity.class);
                        //Common.currentUser = info;
                        startActivity(i);
                        //finish();

                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check Email to verify your account!", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(Login.this, "Faild to log in,please enter correctly!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    public void ForgotPass(View view)
    {
        EditText resetmail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialouge = new AlertDialog.Builder(view.getContext());
        passwordResetDialouge.setTitle("Reset Password");
        passwordResetDialouge.setMessage("Enter Your mail to Reset Link");
        passwordResetDialouge.setView(resetmail);

        passwordResetDialouge.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mail = resetmail.getText().toString();
                mFirebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Login.this, "Reset Link sent to your mail", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error!,Not Sent Reset Link"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordResetDialouge.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        passwordResetDialouge.create().show();
    }







    public void btn_SignUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignUP.class));
    }

   /*
    public void GotoPage()
    {
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null)
                {
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Login.this, "Please log in", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    public void btnlogin(View view)
    {
        //GotoPage();

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
            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(!(email.isEmpty() && pass.isEmpty()))
        {
            mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(Login.this, "Login Error!,Please Login Again", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent  = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(Login.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
*/

}