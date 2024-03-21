package com.example.khadokapp;

import android.content.Context;
import android.content.Intent;
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

public class RecyclerAdapter_PlaceOrder extends RecyclerView.Adapter<RecyclerAdapter_PlaceOrder.ViewHolder>{

    private static final String Tag = "Recyclerview_PlaceOrder";

    public static int getFoodPosition;

    private Context mcontext;
    private ArrayList<PlaceOrderDB>ModelList;

    public RecyclerAdapter_PlaceOrder(Context mcontext, ArrayList<PlaceOrderDB> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter_PlaceOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_placeorder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameview.setText(ModelList.get(position).getName());

        holder.priceView.setText(ModelList.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameview;
        TextView priceView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            priceView = itemView.findViewById(R.id.Food_Price_PlaceOrder);
            nameview = itemView.findViewById(R.id.Food_Name_PlaceOrder);

        }
    }
}
