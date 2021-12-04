package com.example.personalaccounthmi.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.AvatarSelectListener;
import com.example.personalaccounthmi.R;

import java.util.ArrayList;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.MyViewHolder>{
    private  Context mContext;
    private  ArrayList mAvatarList;
    private AvatarSelectListener mCallback;
    private int selectedPosition=-1;


    public AvatarAdapter(Context context, ArrayList avatarList, AvatarSelectListener listener){
        this.mContext =context;
        mAvatarList =avatarList;
        mCallback = listener;
    }

    @NonNull
    @Override
    public AvatarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View v= inflater.inflate(R.layout.avatarviewlayout,parent,false);
        return new AvatarAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarAdapter.MyViewHolder holder, int position) {
        String avatar = (String) mAvatarList.get(position);

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
        if(selectedPosition==position){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#66FFFF00"));
        }
        else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        holder.avatar.setOnClickListener(v -> {
            mCallback.onAvatarClick(avatar);
            selectedPosition=holder.getAdapterPosition();
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return mAvatarList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.avatarCard);
            avatar=itemView.findViewById(R.id.selectAvatar);
        }
    }
}
