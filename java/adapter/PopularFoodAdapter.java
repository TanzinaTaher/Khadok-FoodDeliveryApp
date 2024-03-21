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

import model.PopularFood;

public class PopularFoodAdapter  extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder> {
    Context context;
    List<PopularFood> popularFoodList;

    public PopularFoodAdapter(Context context,List<PopularFood>popularFoodList) {
        this.context = context;
        this.popularFoodList=popularFoodList;
    }

    @NonNull

    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.popular_food_row_item,parent,false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PopularFoodAdapter.PopularFoodViewHolder holder, int position) {
        holder.foodImage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.name.setText(popularFoodList.get(position).getName());
        holder.price.setText(popularFoodList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }

    public static final class PopularFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView price,name;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage=itemView.findViewById(R.id.food_image);
            price=itemView.findViewById(R.id.price);
            name=itemView.findViewById(R.id.name);

        }
    }
}
