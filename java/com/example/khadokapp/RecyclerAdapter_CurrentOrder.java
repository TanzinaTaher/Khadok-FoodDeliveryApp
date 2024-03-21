package com.example.khadokapp;


import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter_CurrentOrder extends RecyclerView.Adapter<RecyclerAdapter_CurrentOrder.ViewHolder>{

    private static final String Tag = "Recyclerview_CurrentOrder";

    public static int getFoodPosition;

    private Context mcontext;
    private ArrayList<Admin_CurrentOrder_Model>ModelList;

    public RecyclerAdapter_CurrentOrder(Context mcontext, ArrayList<Admin_CurrentOrder_Model> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter_CurrentOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_current_orders,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.usermail.setText(ModelList.get(position).getUsername());
        holder.currentTime.setText(ModelList.get(position).getCurrentTime());
        holder.currentDate.setText(ModelList.get(position).getCurrentData());
        holder.Orders.setText(ModelList.get(position).getOrders());
        holder.TotalPrice.setText(ModelList.get(position).getTotalPrice());
        holder.PhoneNumber.setText(ModelList.get(position).getPhonenumber());
        holder.Address.setText(ModelList.get(position).getAddress());



    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView currentTime,currentDate,Orders,usermail,TotalPrice,PhoneNumber,Address;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            currentDate = itemView.findViewById(R.id.Admin_currentDate);
            currentTime =itemView.findViewById(R.id.Admin_currentTime);
            Orders = itemView.findViewById(R.id.Admin_currentOrder);
            usermail =itemView.findViewById(R.id.Admin_currentUser);
            TotalPrice =itemView.findViewById(R.id.Admin_TotalPrice);
            Address = itemView.findViewById(R.id.Admin_currentaddress);
            PhoneNumber  = itemView.findViewById(R.id.Admin_currentphonenumber);

        }
    }
}
