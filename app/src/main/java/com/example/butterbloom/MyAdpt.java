package com.example.butterbloom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdpt extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<UserDataClass> dataList;

    public MyAdpt(Context context, List<UserDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.recName.setText(dataList.get(position).getName());
       holder.recEmail.setText(dataList.get(position).getEmail());
       holder.recPhNo.setText(dataList.get(position).getPhoneNumber());
       holder.recAddress.setText(dataList.get(position).getAddress());

       holder.recCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,UserDetailActivity.class);
               intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getName());
               intent.putExtra("Email",dataList.get(holder.getAdapterPosition()).getEmail());
               intent.putExtra("Phone Number",dataList.get(holder.getAdapterPosition()).getPhoneNumber());
               intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getAddress());
               context.startActivity(intent);

           }
       });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<UserDataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView recName,recEmail,recPhNo,recAddress;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard = itemView.findViewById(R.id.recCard);
        recName = itemView.findViewById(R.id.recName);
        recEmail = itemView.findViewById(R.id.recEamil);
        recPhNo = itemView.findViewById(R.id.recPhNo);
        recAddress = itemView.findViewById(R.id.recAddress);
    }
}

