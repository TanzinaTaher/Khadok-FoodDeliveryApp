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

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>{

    private static final String Tag = "Recyclerview2";

    public static int getFoodPosition;

    private Context mcontext;
    private ArrayList<Model2>ModelList;

    public RecyclerAdapter2(Context mcontext, ArrayList<Model2> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameview.setText(ModelList.get(position).getName());

        holder.priceView.setText(ModelList.get(position).getPrice());

        Picasso.get().load(ModelList.get(position).getImage()).into(holder.imageViewFood);

    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameview;
        TextView priceView;
        ImageView imageViewFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.rImageViewFood);
             priceView = itemView.findViewById(R.id.rPriceTvFood);
            nameview = itemView.findViewById(R.id.rNameTvFood);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(view.getContext(), AddToCart.class);
                    //intent.putExtra("MenuId",getAbsoluteAdapterPosition()+1);

                    getFoodPosition = getAbsoluteAdapterPosition()+1;

                    intent.putExtra("fooditem",ModelList.get(getAbsoluteAdapterPosition()));

                    view.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(), " "+ getAbsoluteAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
