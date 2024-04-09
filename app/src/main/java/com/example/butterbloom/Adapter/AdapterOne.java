package com.example.butterbloom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.butterbloom.Model.Cake;
import com.example.butterbloom.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterOne extends RecyclerView.Adapter<AdapterOne.ViewHolder> {
    private Context context;
    private List<Cake> cakeList;
    public AdapterOne(Context context, List<Cake> cakeList) {
        this.context = context;
        this.cakeList = cakeList;
    }
    @NonNull
    @Override
    public AdapterOne.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hori_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOne.ViewHolder holder, int position) {
        holder.imageView.setImageResource(cakeList.get(position).getImg());
        holder.txtName.setText(cakeList.get(position).getName());
        holder.hori_layout.setOnClickListener(new View.OnClickListener() {
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
        TextView txtName;
        CircleImageView imageView;
        ConstraintLayout hori_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imageView = itemView.findViewById(R.id.profile_image);
            hori_layout = itemView.findViewById(R.id.hori_layout);
        }
    }
}
