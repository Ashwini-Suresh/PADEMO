package com.example.personalaccounthmi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import java.util.ArrayList;

/**
 * @brief the adapter class provides access to the data elements and makes the view of each item
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //creating onject of the context
    public Context context;
    private ArrayList pData;

    //creating the constructor of the adapter class
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

        switch (avatar){
            case "avatar1" : holder.avatar.setImageResource(R.drawable.avatar1);
                break;
            case "avatar2" : holder.avatar.setImageResource(R.drawable.avatar2);
                break;
            case "avatar3" : holder.avatar.setImageResource(R.drawable.avatar3);
                break;




        }

        holder.name.setText(name);


    }

    @Override
    public int getItemCount() {
        return pData.size();
    }

    /**
     * @brief Viewholder is used to describes the item view and the place within the recyclar view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView avatar;

        //creating constructor of the viewholder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.layouttext);
            avatar=itemView.findViewById(R.id.userImage);
        }
    }
}
