package com.example.khadokapp;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter_AdminFood extends RecyclerView.Adapter<RecyclerAdapter_AdminFood.ViewHolder>{

    private static final String Tag = "Recyclerview_Admin_Food";

    //public static int getFoodPosition;

    private Context mcontext;
    private ArrayList<Admin_Model_Food>ModelList;

    private OnItemClickListener listener;


    public RecyclerAdapter_AdminFood(Context mcontext, ArrayList<Admin_Model_Food> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter_AdminFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_food,parent,false);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView nameview;
        TextView priceView;
        ImageView imageViewFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.Admin_rImageViewFood);
            priceView = itemView.findViewById(R.id.Admin_rPriceTvFood);
            nameview = itemView.findViewById(R.id.Admin_rNameTvFood);


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener!=null)
            {
                int position = getAbsoluteAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("Choose an action");
            MenuItem doAnyTask = contextMenu.add(contextMenu.NONE,1,1,"Edit");
            MenuItem delete = contextMenu.add(contextMenu.NONE,2,2,"Delete");

            doAnyTask.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);


        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if(listener!=null)
            {
                int position = getAbsoluteAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                {
                    switch (menuItem.getItemId())
                    {
                        case 1:

                            listener.onDoAnyTask(position);


                            return true;
                        case 2:

                            listener.onDelete(position);

                            return true;

                    }
                }
            }


            return false;
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDoAnyTask(int position);
        void onDelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
