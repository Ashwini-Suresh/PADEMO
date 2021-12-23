/**
 * @file CustomAdapter.java
 * @brief The adapter class which inflates the  profile list in the recycler view
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.CustomAdapterListener;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import java.util.ArrayList;

/**
 *  The adapter class which inflates the  profile list in the recycler view
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    /**
     * declaring variables of Context, Arraylist and BindServiceInterface
     */
    private final Context context;
    private final ArrayList pData;
    CustomAdapterListener listener;

    /**
     * creating constructor of the class
     * @param context
     * @param profileData
     */
    CustomAdapter(Context context,ArrayList profileData, CustomAdapterListener listener){
        this.context=context;
        pData=profileData;
        this.listener = listener;

        /**
         * Assigning BindServiceInterface instance to the variable
         */
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);

        /**
         * creating object View and inflate profilelist_layout to the view
         */
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

        /**
         * String name is assigned with the username in the profile list
         */
        holder.name.setText(name);

        /**
         * setting the image view with image corresponding to the String avatar
         */
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

        /**
         * onClicking the avatar image on the Profile view, the profile is selected
         */
        holder.avatar.setOnClickListener(v -> {
            listener.switchActiveProfile(id);

        });

        /**
         * Card view of the selected profile is changed
         */
        if (isActive){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#66FFFF00"));
            Toast.makeText(context.getApplicationContext(), "Profile Changed to "+name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return pData.size();
    }

    /**
     * Inner class MyViewHolder defines the layout to be populated in the avatar recycler view
     */
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
