package com.example.personalaccounthmi.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pData;

    MainActivityInterface mainActivityInterface;

    CustomAdapter(Context context,ArrayList profileData){
        this.context=context;
        pData=profileData;
        mainActivityInterface = MainActivityInterface.getInstance(context);
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.profilelist_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProfileData profile= (ProfileData) pData.get(position);
        String name= profile.getName();
        String avatar = profile.getAvatar();
        int id = profile.getId();
        boolean isActive = profile.isActive();

        holder.name.setText(name);
        switch (avatar){
            case "avatar1" : holder.avatar.setImageResource(R.mipmap.avatar1);
                break;
            case "avatar2" : holder.avatar.setImageResource(R.mipmap.avatar2);
                break;
            case "avatar3" : holder.avatar.setImageResource(R.mipmap.avatar3);
                break;
            case "avatar4" : holder.avatar.setImageResource(R.mipmap.avatar4);
                break;
            case "avatar5" : holder.avatar.setImageResource(R.mipmap.avatar5);
                break;
            case "avatar6" : holder.avatar.setImageResource(R.mipmap.avatar6);
                break;
            case "avatar7" : holder.avatar.setImageResource(R.mipmap.avatar7);
                break;
            case "avatar8" : holder.avatar.setImageResource(R.mipmap.avatar8);
                break;
        }
        holder.avatar.setOnClickListener(v -> {
            mainActivityInterface.changeActiveProfile(id);

        });
        if (isActive){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#66FFFF00"));

        }


    }

    @Override
    public int getItemCount() {
        return pData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView avatar;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);

            name=itemView.findViewById(R.id.layouttext);
            avatar=itemView.findViewById(R.id.userImage);
        }
    }
}
