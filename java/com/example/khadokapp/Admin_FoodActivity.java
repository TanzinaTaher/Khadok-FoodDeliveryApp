package com.example.khadokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Admin_FoodActivity extends AppCompatActivity {


    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;


    public static String Edit_key;

    private FirebaseStorage firebaseStorage;


    String getvalue = "";
    private Context context = Admin_FoodActivity.this;

    private ArrayList<Admin_Model_Food> ModelList;

    private RecyclerAdapter_AdminFood recyclerAdapter;

    //int getposvalue = RecyclerAdapter.getvalue1;

    //public static int posotionValueAddtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Admin Food List");

        //getvalue = getIntent().getStringExtra("MenuId");

        mrecyclerView = findViewById(R.id.Admin_recyclerview);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();


        ModelList = new ArrayList<>();

        firebaseStorage = FirebaseStorage.getInstance();


        ClearAll();

        GetDataFromFirebase();

        //posotionValueAddtoCart = getposvalue;



    }

    private void GetDataFromFirebase() {

       // String mypos = Integer.toString(getposvalue);
        Query query = mRef.child("Food");
       // Toast.makeText(FoodActivity.this, " "+getposvalue, Toast.LENGTH_SHORT).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                ModelList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Admin_Model_Food model = new Admin_Model_Food();

                    String str = snapshot1.child("id").getValue().toString();

                   // if(str.equals(mypos))
                    {
                        model.setImage(snapshot1.child("image").getValue().toString());
                        model.setPrice(snapshot1.child("price").getValue().toString());


                        model.setName(snapshot1.child("name").getValue().toString());

                        model.setKey(snapshot1.getKey());

                        ModelList.add(model);
                    }

                }

                recyclerAdapter = new RecyclerAdapter_AdminFood(getApplicationContext(),ModelList);
                mrecyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();


                recyclerAdapter.setOnItemClickListener(new RecyclerAdapter_AdminFood.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text = ModelList.get(position).getName();
                        Toast.makeText(getApplicationContext(),text+" is selected"+" "+position,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDoAnyTask(int position) {
                        Toast.makeText(getApplicationContext()," On do any task is selected",Toast.LENGTH_SHORT).show();

                        Toast.makeText(getApplicationContext()," on edit is selected",Toast.LENGTH_SHORT).show();
                        Admin_Model_Food selectedItem = ModelList.get(position);
                        final String key = selectedItem.getKey();

                        Edit_key = key;

                        Intent intent = new Intent(Admin_FoodActivity.this,AdminEditItem.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onDelete(int position) {

                        Admin_Model_Food selectedItem = ModelList.get(position);
                        final String key = selectedItem.getKey();

                      /*  StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getImage());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                mRef.child(key).removeValue();
                            }
                        });*/

                        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getImage());
                        storageReference.delete();

                        mRef = FirebaseDatabase.getInstance().getReference()
                                .child("Food").child(key);
                        mRef.removeValue();

                        Toast.makeText(getApplicationContext()," on delete is selected",Toast.LENGTH_SHORT).show();
                    }

                });
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
        ArrayList<Admin_Model_Food>mylist = new ArrayList<>();

        for(Admin_Model_Food model2:ModelList)
        {
            if(model2.getName().toLowerCase().contains(s.toLowerCase()))
            {
                mylist.add(model2);
            }
        }


        RecyclerAdapter_AdminFood recyclerAdapter_search = new RecyclerAdapter_AdminFood(getApplicationContext(),mylist);
        mrecyclerView.setAdapter(recyclerAdapter_search);
        recyclerAdapter.notifyDataSetChanged();

    }


}