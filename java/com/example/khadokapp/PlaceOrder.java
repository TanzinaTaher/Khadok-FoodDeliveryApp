package com.example.khadokapp;



import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;

import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Queue;

import SplashScreen.Welcome_Text;


public class PlaceOrder extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;


    String Username_login;
    private Context context = PlaceOrder.this;

    private ArrayList<PlaceOrderDB>ModelList;

    private RecyclerAdapter_PlaceOrder recyclerAdapter;

    Button welcome_textbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Placed Order");




        Username_login = Login.User_name;

        mrecyclerView = findViewById(R.id.recyclerview_place_order);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();


        ModelList = new ArrayList<>();


        ClearAll();

        GetDataFromFirebase();





    }





    private void GetDataFromFirebase() {

        Query query = mRef.child("PlacedOrder");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    PlaceOrderDB model = new PlaceOrderDB();

                    String str = snapshot1.child("username").getValue().toString();

                    if(Username_login.equals(str))
                    {

                        model.setName(snapshot1.child("name").getValue().toString());
                        model.setPrice(snapshot1.child("price").getValue().toString());
                        model.setUsername(snapshot1.child("username").getValue().toString());

                        ModelList.add(model);

                    }



                }



                recyclerAdapter = new RecyclerAdapter_PlaceOrder(getApplicationContext(),ModelList);
                mrecyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll()
    {
        if(ModelList!=null)
        {
            ModelList.clear();

            if(recyclerAdapter != null)
            {
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        ModelList = new ArrayList<>();
    }
}