package com.example.personalaccounthmitrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pData;

    CustomAdapter(Context context,ArrayList profileData){
        this.context=context;
        pData=profileData;

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

        holder.name.setText(name);
        holder.avatar.setText(avatar);

    }

    @Override
    public int getItemCount() {
        return pData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.layouttext);
            avatar=itemView.findViewById(R.id.layoutimage);
        }
    }
}
