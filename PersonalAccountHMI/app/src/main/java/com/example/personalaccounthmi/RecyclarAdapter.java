package com.example.personalaccounthmi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclarAdapter extends RecyclerView.Adapter<RecyclarAdapter.MyViewHolder> {

    private int[] images;

    public RecyclarAdapter(int[] images){
        this.images = images;

    }

    public RecyclarAdapter(MainActivity mainActivity, ArrayList<ProfileData> list) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilelist_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.UserImage.setImageResource(images[position]);
        holder.UserName.setText("Image: "+position);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView UserImage;
        TextView UserName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            UserImage = itemView.findViewById(R.id.layoutimage);
            UserName = itemView.findViewById(R.id.layouttext);
        }
    }
}
