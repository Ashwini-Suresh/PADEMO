/**
 * @file EditAvatarDialog.java
 * @brief This class describes the functionalities in the dialog fragment for editing the avatar image of a profile
 * @author Karthika V T
 */
package com.example.personalaccounthmi.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.AvatarSelectListener;
import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.view.AvatarAdapter;

import java.util.ArrayList;

/**
 * @brief The class includes the functionality to edit the avatar image of an existing profile
 */
public class EditAvatarDialog extends DialogFragment implements AvatarSelectListener {

    /**
     * declaring layout elements and avatar list
     */
    ArrayList<String> mAvatarList;
    RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager;
    private AvatarAdapter avatarAdapter;
    String mAvatar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * creating object of the view and inflate editavatarlayout to the fragment
         */
        View fragmentView = inflater.inflate(R.layout.editavatarlayout,container,false);

        /**
         * finding all UI elements
         */
        ImageButton close = fragmentView.findViewById(R.id.closeAvatar);
        Button cancel = fragmentView.findViewById(R.id.cancelAvatar);
        Button save = fragmentView.findViewById(R.id.saveAvatar);
        recyclerView1 = fragmentView.findViewById(R.id.editAvatarList);
        Context context = getContext();
        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        /**
         *  assigning single instance to mainActivityInterface
         */
        BindServiceInterface bindServiceInterface = BindServiceInterface.getInstance(getContext());

        /**
         * String value of avatar list is assigned to mAvatarList with function getAvatarList
         */
        try {
            mAvatarList = (ArrayList<String>) bindServiceInterface.getAvatarList();
        } catch (Exception e) {
            Log.i("Exception","did not get avatar list");
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            avatarAdapter = new AvatarAdapter(context,mAvatarList, this);
            recyclerView1.setAdapter(avatarAdapter);
            recyclerView1.setLayoutManager(layoutManager);
        }, 200);

        /**
         * onClicking close button the dialog dismisses
         */
        close.setOnClickListener(v -> dismiss());

        /**
         * onClicking cancel button the dialog dismisses
         */
        cancel.setOnClickListener(v -> dismiss());

        /**
         * onClicking save button the string value corresponding to the selected avatar will be saved to db.
         */
        save.setOnClickListener(v -> {
            if(mAvatar!=null){
                bindServiceInterface.updateProfileAvatar(mAvatar);
                Toast.makeText(getContext(), "AVATAR UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                dismiss();
            }else
                Toast.makeText(getContext(), "SELECT AVATAR", Toast.LENGTH_SHORT).show();
        });


        return fragmentView;
    }

    @Override
    public void onAvatarClick(String avatar) {
        mAvatar =avatar;
    }
}

