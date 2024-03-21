package com.example.khadokapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Admin_PostActivity extends AppCompatActivity implements View.OnClickListener{

    Button choosebutton,saveButon,displayButton;
    ImageView imageView;
    EditText Title,Description;
    ProgressBar progressBar;
    Uri imageuri;


    DatabaseReference databaseReference;
    StorageReference storageReference;


    StorageTask uploadTask;





    private static final int IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post);

        choosebutton  = findViewById(R.id.choose_image_btn_post);
        saveButon = findViewById(R.id.saveAll_post);
        displayButton = findViewById(R.id.displayAll_post);

        imageView = findViewById(R.id.imageViewid_post);


        Title = findViewById(R.id.TitleEditText);
        Description = findViewById(R.id.DescriptionEditText);


        saveButon.setOnClickListener(this);
        displayButton.setOnClickListener(this);
        choosebutton.setOnClickListener(this);


        databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        storageReference = FirebaseStorage.getInstance().getReference("Data");



    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.choose_image_btn_post:

                openFileChooser();

                break;
            case R.id.saveAll_post:

                if(uploadTask!=null && uploadTask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Upload In Progress",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    saveData();
                }

                break;
            case R.id.displayAll_post:

                goto_Admin_Food();
                break;
        }
    }

    private void goto_Admin_Food() {
        Intent intent = new Intent(Admin_PostActivity.this,Admin_PostActivity_Show.class);
        startActivity(intent);

    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageuri = data.getData();
            Picasso.get().load(imageuri).into(imageView);
        }

    }


    public String getFileExtension(Uri imageuri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }

    private void saveData() {

        String title = Title.getText().toString();
        String description= Description.getText().toString();

        if(title.isEmpty())
        {
            Title.setError("Enter food name");
            Title.requestFocus();
            return;
        }
        if(description.isEmpty())
        {
            Description.setError("Enter food Id");
            Description.requestFocus();
            return;
        }

        StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageuri));


       /* ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image is stored",Toast.LENGTH_SHORT).show();

                Admin_Upload upload  = new Admin_Upload(name,taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),price,id);


                String uploadId = databaseReference.push().getKey();

                databaseReference.child(uploadId).setValue(upload);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Image not stored successfully",Toast.LENGTH_SHORT).show();
            }
        });*/
        ref.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        },500);
                        Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoStringLink = uri.toString();


                                Toast.makeText(Admin_PostActivity.this, "Upload successful", Toast.LENGTH_LONG).show();


                                Admin_PostUpload upload  = new Admin_PostUpload(title,photoStringLink,description);


                                String uploadId = databaseReference.push().getKey();

                                databaseReference.child(uploadId).setValue(upload);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}