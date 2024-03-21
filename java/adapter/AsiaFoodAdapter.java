package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.khadokapp.R;

import java.util.List;

import model.AsiaFood;

public class AsiaFoodAdapter extends RecyclerView.Adapter<AsiaFoodAdapter.AsiaFoodViewHolder> {
    Context context;
    List<AsiaFood> AsiaFoodList;

    public AsiaFoodAdapter(Context context,List<AsiaFood>popularFoodList) {
        this.context = context;
        this.AsiaFoodList=popularFoodList;
    }

    @NonNull

    @Override
    public AsiaFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.asia_food_row_item,parent,false);
        return new AsiaFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsiaFoodAdapter.AsiaFoodViewHolder holder, int position) {
        holder.foodImage.setImageResource(AsiaFoodList.get(position).getImageUrl());
        holder.name.setText(AsiaFoodList.get(position).getName());
        holder.price.setText(AsiaFoodList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return AsiaFoodList.size();
    }

    public static final class AsiaFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView price,name,rating,restaurentname;

        public AsiaFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage=itemView.findViewById(R.id.food_image);
            price=itemView.findViewById(R.id.price);
            name=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rating);
            restaurentname=itemView.findViewById(R.id.restaurent_name);

        }
    }
}