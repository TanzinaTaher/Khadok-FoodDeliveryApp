package com.example.khadokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdminEditItem extends AppCompatActivity {

    DatabaseReference databaseReference,databaseReference1,imageRef;
    FirebaseDatabase firebaseDatabase;

    EditText Food_Name, Food_Price;
    Button Update;

    ImageView Food_Image;

    String key;

    String name,price;

    String price1,name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_item);

        Food_Name = findViewById(R.id.Edit_food_name);

        Food_Price = findViewById(R.id.Edit_food_price);

        Food_Image = findViewById(R.id.Edit_food_image);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference1 = firebaseDatabase.getReference();

        imageRef = firebaseDatabase.getReference();


        key = Admin_FoodActivity.Edit_key;

        getDataFromFirebase();
        getDataFromFirebase2();





    }



    public void Updatebtn(View view)
    {


        price1 = String.valueOf(Food_Price.getText());
        name1 = String.valueOf(Food_Name.getText());

        databaseReference1.child("Food").child(key).child("name").setValue(name1);
        databaseReference1.child("Food").child(key).child("price").setValue(price1);

        Toast.makeText(AdminEditItem.this,"Data Updated",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AdminEditItem.this,Admin_FoodActivity.class);
        startActivity(intent);

    }


    private void getDataFromFirebase() {




        Query query = databaseReference.child("Food").child(key);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    price = snapshot.child("price").getValue().toString();
                    name =snapshot.child("name").getValue().toString();


                    Food_Name.setText(name);

                    Food_Price.setText(price);
                }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void getDataFromFirebase2()
    {
        Query query = imageRef.child("Food").child(key).child("image");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String link = snapshot.getValue(String.class);

                Picasso.get().load(link).into(Food_Image);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}