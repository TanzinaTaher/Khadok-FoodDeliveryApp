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

import org.w3c.dom.Text;

import java.util.ArrayList;

import Interface.ItemClickListener;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    
    private static final String Tag = "Recyclerview";
    private ItemClickListener itemClickListener;

    public static int getvalue1;


    private Context mcontext;
    private ArrayList<Model>ModelList;

    public RecyclerAdapter(Context mcontext, ArrayList<Model> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.titleview.setText(ModelList.get(position).getTitle());

    holder.descriptionview.setText(ModelList.get(position).getDescription());

    Picasso.get().load(ModelList.get(position).getImage()).into(holder.foodimgview);




    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

            TextView titleview;
            TextView descriptionview;
            ImageView foodimgview;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);



                foodimgview = itemView.findViewById(R.id.rImageView);
                titleview = itemView.findViewById(R.id.rTitleTv);
                descriptionview = itemView.findViewById(R.id.rDescriptionTv);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int position_value = 1;

                        Intent intent  = new Intent(view.getContext(),FoodActivity.class);
                        //intent.putExtra("MenuId",getAbsoluteAdapterPosition()+1);

                        getvalue1= getAbsoluteAdapterPosition()+1;
                        view.getContext().startActivity(intent);
                        Toast.makeText(view.getContext(), " "+ getAbsoluteAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                });


                //itemView.setOnClickListener(this);
            }

       /* @Override
        public void onClick(View view) {
                //itemClickListener.OnClick(view,getAbsoluteAdapterPosition(),false);
                Intent intent  = new Intent(this,FoodActivity.class);
                intent.putExtra("Menu_Id",getBindingAdapterPosition());
                 //Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();
        }*/
    }

}
