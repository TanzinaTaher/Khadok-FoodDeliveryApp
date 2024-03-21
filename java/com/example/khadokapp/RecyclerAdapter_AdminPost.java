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

public class RecyclerAdapter_AdminPost extends RecyclerView.Adapter<RecyclerAdapter_AdminPost.ViewHolder>{

    private static final String Tag = "Recyclerview_Admin_Post";

    //public static int getFoodPosition;

    private Context mcontext;
    private ArrayList<Admin_Model_Post>ModelList;

    private OnItemClickListener listener;


    public RecyclerAdapter_AdminPost(Context mcontext, ArrayList<Admin_Model_Post> modelList) {
        this.mcontext = mcontext;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter_AdminPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_post,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.titleview.setText(ModelList.get(position).getTitle());

        holder.descriptionview.setText(ModelList.get(position).getDescription());

        Picasso.get().load(ModelList.get(position).getImage()).into(holder.imageViewPost);

    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView titleview;
        TextView descriptionview;
        ImageView imageViewPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPost = itemView.findViewById(R.id.rImageView_AdminPost);
            titleview = itemView.findViewById(R.id.rTitleTv_AdminPost);
            descriptionview = itemView.findViewById(R.id.rDescriptionTv_AdminPost);


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
