package com.example.butterbloom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.butterbloom.Model.Cake;
import com.example.butterbloom.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.ViewHolder> {
    private Context context;
    private List<Cake> cakeList;
    public AdapterTwo(Context context, List<Cake> cakeList) {
        this.context = context;
        this.cakeList = cakeList;
    }
    @NonNull
    @Override
    public AdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_item,parent,false);
        return new AdapterTwo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTwo.ViewHolder holder, int position) {
        holder.imageView.setImageResource(cakeList.get(position).getImg());
        holder.txtPrice.setText(cakeList.get(position).getPrice());
        holder.cakeName.setText(cakeList.get(position).getName());
        holder.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cakeName,txtPrice;
        ImageView imageView;
        Button btnCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeName = itemView.findViewById(R.id.cake_name);
            imageView = itemView.findViewById(R.id.imageView5);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnCart = itemView.findViewById(R.id.btnCart);
        }
    }
}
