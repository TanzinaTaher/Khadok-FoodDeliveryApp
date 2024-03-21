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


public class PostListActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;



    private Context context = PostListActivity.this;

    private ArrayList<Model>ModelList;

    private RecyclerAdapter recyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Food List");






        mrecyclerView = findViewById(R.id.recyclerview);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();


        ModelList = new ArrayList<>();


        ClearAll();

        GetDataFromFirebase();




    }


    private void GetDataFromFirebase() {

        Query query = mRef.child("Data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Model model = new Model();
                    model.setImage(snapshot1.child("image").getValue().toString());
                    model.setTitle(snapshot1.child("title").getValue().toString());
                    model.setDescription(snapshot1.child("description").getValue().toString());

                    ModelList.add(model);
                }



                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),ModelList);
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
                ProcessSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ProcessSearch(s);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
    public void ProcessSearch(String s)
    {
        ArrayList<Model>mylist = new ArrayList<>();

        for(Model model2:ModelList)
        {
            if(model2.getDescription().toLowerCase().contains(s.toLowerCase()))
            {
                mylist.add(model2);
            }
        }


        RecyclerAdapter recyclerAdapter_search = new RecyclerAdapter(getApplicationContext(),mylist);
        mrecyclerView.setAdapter(recyclerAdapter_search);
        recyclerAdapter.notifyDataSetChanged();

    }

}