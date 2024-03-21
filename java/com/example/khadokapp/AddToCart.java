package com.example.khadokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import TotalPrice.TotalPrice;

public class AddToCart extends AppCompatActivity {

    ImageView plusselect,minusselect,FoodImage;
    TextView item_count,FoodName,get_price;


    String UserName = "";

    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    DatabaseReference addtocart;

    String Price ="";
    String Name = "";
    String Images = "";
    int count_number = 0;


    String Price1,Name1;

    int calculate_total = 0 ;

    int position_value = RecyclerAdapter2.getFoodPosition;

    Model2 model2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        plusselect  =findViewById(R.id.plus_img);
        minusselect = findViewById(R.id.minus_img);

        item_count = findViewById(R.id.item_count);

        FoodName = findViewById(R.id.Food_name);

        get_price = findViewById(R.id.getprice);


        FoodImage = findViewById(R.id.addtocart_image);

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();

        UserName = Login.User_name;

        //GetDataFromFirebase();



        addtocart = FirebaseDatabase.getInstance().getReference().child("AddToCart");

        final Object obj = getIntent().getSerializableExtra("fooditem");
        if(obj instanceof Model2)
        {
            model2 = (Model2)obj;
        }
        if(model2!=null)
        {
            Price = model2.getPrice();
            Name = model2.getName();
            Images = model2.getImage();


            FoodName.setText(Name);
            get_price.setText(Price);

            Picasso.get().load(Images).into(FoodImage);

            Price1 = Price;
            Name1 = Name;
        }




    }

    public void showCart(View view)
    {
        Intent intent = new Intent(AddToCart.this,ShowCart.class);
        view.getContext().startActivity(intent);
    }

    public void plusClick(View view)
    {
        count_number++;

        int total_price,get_Food_price;

        get_Food_price = Integer.parseInt(Price);

        total_price = count_number*get_Food_price;

        String NewPrice = Integer.toString(total_price);

        Price1 = NewPrice;

        get_price.setText(Price1);

        String getCountNumber = Integer.toString(count_number);
        item_count.setText(getCountNumber);

    }

    public void minusClick(View view)
    {
        count_number--;
        if(count_number<=0)
        {
            count_number = 1;
        }

        int total_price,get_Food_price;

        get_Food_price = Integer.parseInt(Price);

        total_price = count_number*get_Food_price;

        String NewPrice = Integer.toString(total_price);

        Price1 = NewPrice;


        get_price.setText(Price1);

        String getCountNumber = Integer.toString(count_number);
        item_count.setText(getCountNumber);
    }

    public void AddtoCartbtnClick(View view)
    {
        int getTotal_price = Integer.parseInt(Price);
        TotalPrice.totalPrice = TotalPrice.totalPrice + getTotal_price;
        Price = Price1;
        AddToCartDB1 addToCartDB = new AddToCartDB1(Name,Price,UserName);
        addtocart.push().setValue(addToCartDB);

        Toast.makeText(AddToCart.this,"Added Successfully",Toast.LENGTH_SHORT).show();
    }


   /* private void GetDataFromFirebase() {

        String mypos = Integer.toString(position_value);
        Query query = mRef.child("Food");
        Toast.makeText(AddToCart.this, " "+position_value, Toast.LENGTH_SHORT).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Model model = new Model();

                    String str = snapshot1.child("id").getValue().toString();
                   // Toast.makeText(AddToCart.this,""+str+"id number",Toast.LENGTH_SHORT).show();
                    if(str.equals(mypos))
                    {
                        Price =snapshot1.child("price").getValue().toString();

                        Toast.makeText(AddToCart.this,""+Price,Toast.LENGTH_SHORT).show();
                        Name = snapshot1.child("name").getValue().toString();

                       // Toast.makeText(AddToCart.this,""+Name,Toast.LENGTH_SHORT).show();

                        FoodName.setText(Name);
                        get_price.setText(Price);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

}