package com.example.khadokapp;


import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ViewHolder>{

    private static final String Tag = "Recyclerview3";

    private OnItemClickListener listener;

    private Context mcontext;
    private ArrayList<AddToCartDB>ModelList;

    public RecyclerAdapter3(Context mcontext, ArrayList<AddToCartDB> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Food_Name.setText(ModelList.get(position).getName());

        holder.Food_Price.setText(ModelList.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView Food_Name;
        TextView Food_Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Food_Name = itemView.findViewById(R.id.Food_Name);
            Food_Price = itemView.findViewById(R.id.Food_Price);


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


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

                            listener.onDelete(position);

                            return true;
                    }
                }
            }


            return false;
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
            MenuItem delete = contextMenu.add(contextMenu.NONE,1,1,"Delete this item");

            delete.setOnMenuItemClickListener(this);

        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}








