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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;

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


public class Admin_CurrentOrder_Show extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    String getSearch;


    String getvalue = "";
    private Context context = Admin_CurrentOrder_Show.this;

    private ArrayList<Admin_CurrentOrder_Model>ModelList;

    private RecyclerAdapter_CurrentOrder recyclerAdapter;

    int getposvalue = RecyclerAdapter.getvalue1;

    public static int posotionValueAddtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_current_order_show);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Current Orders");

        getvalue = getIntent().getStringExtra("MenuId");

        mrecyclerView = findViewById(R.id.recyclerview_current_orders);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();


        ModelList = new ArrayList<>();


        ClearAll();

        GetDataFromFirebase();

        posotionValueAddtoCart = getposvalue;



    }

    private void GetDataFromFirebase() {

        String mypos = Integer.toString(getposvalue);
        Query query = mRef.child("CurrentOrders");
        Toast.makeText(Admin_CurrentOrder_Show.this, " current order", Toast.LENGTH_SHORT).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Admin_CurrentOrder_Model model = new Admin_CurrentOrder_Model();


                    model.setUsername(snapshot1.child("username").getValue().toString());
                    model.setOrders(snapshot1.child("orders").getValue().toString());
                    model.setCurrentData(snapshot1.child("currentDate").getValue().toString());
                    model.setCurrentTime(snapshot1.child("currentTime").getValue().toString());
                    model.setTotalPrice(snapshot1.child("TotalPrice").getValue().toString());
                    model.setAddress(snapshot1.child("Address").getValue().toString());
                    model.setPhonenumber(snapshot1.child("PhoneNumber").getValue().toString());

                    ModelList.add(model);



                }

                recyclerAdapter = new RecyclerAdapter_CurrentOrder(getApplicationContext(),ModelList);
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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search);

        SearchView searchView =(SearchView)menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getSearch = s;
                ProcessSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getSearch = s;
                ProcessSearch(s);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
    public void ProcessSearch(String s)
    {
        ArrayList<Admin_CurrentOrder_Model>mylist = new ArrayList<>();

        for(Admin_CurrentOrder_Model model2:ModelList)
        {
            if(model2.getUsername().toLowerCase().contains(s.toLowerCase()))
            {
                mylist.add(model2);
            }
        }


        RecyclerAdapter_CurrentOrder recyclerAdapter_search = new RecyclerAdapter_CurrentOrder(getApplicationContext(),mylist);
        mrecyclerView.setAdapter(recyclerAdapter_search);
        recyclerAdapter.notifyDataSetChanged();

    }

}