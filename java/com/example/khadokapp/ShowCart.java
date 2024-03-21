package com.example.khadokapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Queue;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Dashboard.DashboardActivity;
import Dashboard.ProfileActivity;
import SplashScreen.Welcome_Text;
import TotalPrice.TotalPrice;


public class ShowCart extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef,placeorder,mref,deleteOrder,deleteOrder1,current_order;



    String UserName = "";

    String get_the_key = "";
    String[] name_array = new String[100];
    String[] price_array = new String[100];
    String[] username_array = new String[100];

    String[] settheley = new String[100];
    int z = 0;
    int l = 0;
    int o = 0;
    int v = 0;
    int getvar_key = 0;
    String Name_PlaceOrder,Price_PlaceOrder,UserName_PlaceOrder;


    private Context context = ShowCart.this;

    private ArrayList<AddToCartDB>ModelList;

    private RecyclerAdapter3 recyclerAdapter;



   public static String MyMessage = "";



   public String tosend,sEmail,sPassword,Messages_khadok,subject;

    EditText address,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Cart");



        address = findViewById(R.id.show_cart_adress);
        phonenumber = findViewById(R.id.show_cart_phonenumber);


        mrecyclerView = findViewById(R.id.recyclerview3);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();


        mref =  mfirebaseDatabase.getReference();

        placeorder = FirebaseDatabase.getInstance().getReference().child("PlacedOrder");


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        current_order = mfirebaseDatabase.getReference("CurrentOrders");

        ModelList = new ArrayList<>();

        UserName = Login.User_name;

        ClearAll();

        GetDataFromFirebase();





        tosend = Login.User_name;
        subject  = "Your Order From Khadol";

        Messages_khadok = MyMessage;

        sEmail = "encryptedhacker777@gmail.com";
        sPassword = "residentcontrolinfiniteforbidden2022";


















        //Navigation_bar



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home_cart:
                        return true;
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(0,0);


                }

                return false;
            }
        });











    }


   public void PlaceOrder(View View)
    {
        Welcome_text();

        sendMail();
        addtoCart();
        place_the_order();


       /* Intent intent = new Intent(ShowCart.this,SendMail.class);
        startActivity(intent);*/

    }

    public void Welcome_text()
    {

        Intent intent = new Intent(ShowCart.this, Welcome_Text.class);
        startActivity(intent);
    }


    private void addtoCart(){

        String total_price = String.valueOf(TotalPrice.totalPrice);

        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM, dd, yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();

        cartMap.put("username",UserName);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("orders",MyMessage);
        cartMap.put("TotalPrice",total_price);
        cartMap.put("Address",address.getText().toString());
        cartMap.put("PhoneNumber",phonenumber.getText().toString());
        // Toast.makeText(DetailedActivity.this, getUserName, Toast.LENGTH_SHORT).show();

        current_order.push().setValue(cartMap);
        Toast.makeText(ShowCart.this, "Placed!", Toast.LENGTH_SHORT).show();


    }


    private void place_the_order() {

        String Name_order,Price_order,Username_order,getthekey;
        for(int c=0;c<z;c++)
        {

            Name_order = name_array[c];
            Price_order = price_array[c];
            Username_order = username_array[c];
            getthekey = settheley[c];


            PlaceOrder_Model placeOrder_model = new PlaceOrder_Model(Price_order,Username_order,Name_order);
            placeorder.push().setValue(placeOrder_model);

        }
       // deleteOrder();

        Intent intent = new Intent(ShowCart.this,PlaceOrder.class);
        startActivity(intent);


    }


    public void deleteOrder()
    {
        Query query = deleteOrder.child("AddToCart");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    String username =  snapshot1.child("username").getValue().toString();

                    if(username.equals(UserName))
                    {

                            get_the_key = settheley[getvar_key++];

                            deleteOrder1= FirebaseDatabase.getInstance().getReference()
                                    .child("AddToCart").child(get_the_key);
                            deleteOrder1.removeValue();

                            if(getvar_key>=v)
                            {
                                break;
                            }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   /* private void place_the_order() {

        //String mypos = Integer.toString(getposvalue);
        Query query = mref.child("AddToCart");
        //Toast.makeText(FoodActivity.this, " "+getposvalue, Toast.LENGTH_SHORT).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){


              //      if(str.equals(mypos))
                    {
                        String Price = snapshot1.child("price").getValue().toString();


                        String Name =   snapshot1.child("name").getValue().toString();

                        String UserName = snapshot1.child("username").getValue().toString();

                        PlaceOrder_Model placeOrder_model = new PlaceOrder_Model(Price,UserName,Name);
                        placeorder.push().setValue(placeOrder_model);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
*/

    private void GetDataFromFirebase() {

        Query query = mRef.child("AddToCart");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    AddToCartDB model = new AddToCartDB();


                    String UserNAME = snapshot1.child("username").getValue().toString() ;


                    String str = snapshot1.child("username").getValue().toString() ;
                    String str1 = snapshot1.child("price").getValue().toString();
                    String str2 = snapshot1.child("name").getValue().toString();









                   if(UserName.equals(str))
                    {
                        MyMessage = MyMessage+"\n"+str2+" "+str1+" ";
                        model.setKey(snapshot1.getKey());
                        name_array[z++] = str2;
                        price_array[l++] = str1;
                        username_array[o++] = str;

                        String str4 = snapshot1.getKey();
                        settheley[v++] = str4;

                        model.setName(snapshot1.child("name").getValue().toString());
                        model.setPrice(snapshot1.child("price").getValue().toString());
                        ModelList.add(model);



                    }


                }



                recyclerAdapter = new RecyclerAdapter3(getApplicationContext(),ModelList);
                mrecyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();






               recyclerAdapter.setOnItemClickListener(new RecyclerAdapter3.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text = ModelList.get(position).getName();
                        Toast.makeText(getApplicationContext(),text+" is selected"+" "+position,Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onDelete(int position) {

                        AddToCartDB selectedItem = ModelList.get(position);
                        final String key = selectedItem.getKey();


                        mRef = FirebaseDatabase.getInstance().getReference()
                                .child("AddToCart").child(key);
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












    public void sendMail()
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp,port","587");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail,sPassword);
            }
        });


        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sEmail));;


            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tosend));

            message.setSubject(subject);

            message.setText(Messages_khadok);

            new SendMailTO().execute(message);

        }catch(MessagingException e)
        {
            e.printStackTrace();
        }

    }


    private class SendMailTO extends AsyncTask<Message,String,String> {

        /**
         * @param messages
         * @deprecated
         */

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog  = ProgressDialog.show(ShowCart.this,"Please Wait","Sending mail...!",true,false);


        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            }catch (MessagingException e)
            {

                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowCart.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("font color='#509324'>Success</font>"));
                builder.setMessage("Mail Sent Successfully");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                builder.show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Something wrong!!",Toast.LENGTH_SHORT).show();
            }

        }
    }


}