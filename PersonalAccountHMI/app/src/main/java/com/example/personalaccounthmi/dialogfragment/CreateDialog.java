/**
 * @file CreateProfileDialog
 * @brief The java class which contains the functionalities of the dialog fragment for creating a profile
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.AvatarSelectListener;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.view.AvatarAdapter;

import java.util.ArrayList;

public class CreateDialog extends DialogFragment implements AvatarSelectListener {

    /**
     * declaring layout elements
     */
    private EditText name;
    private String avatar;
    private  ArrayList<String> mAvatarList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private AvatarAdapter avatarAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        /**
         * creating object of View and inflate createdialog layout in it
         */
        View rootView = inflater.inflate(R.layout.createdialog, container , false);

        /**
         * finding layout elements
         */
        ImageButton close = rootView.findViewById(R.id.closebutton);
        Button cancel = rootView.findViewById(R.id.cancelbtn);
        Button save = rootView.findViewById(R.id.savebtn);
        name = rootView.findViewById(R.id.Name);
        mRecyclerView = rootView.findViewById(R.id.avatarList);

        /**
         * creating object of context
         */
        Context context = getContext();
        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        /**
         * assigning single instance to mainActivityInterface
         */
        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());

        /**
         * getAvatarList function is called to get the list of string corresponding to the avatar images
         */
        try {
            mAvatarList = (ArrayList<String>) mainActivityInterface.getAvatarList();
        } catch (Exception e) {
            Log.i("Exception","did not get avatar list");
        }

        /**
         * Handler object is created to run the recycler view in a thread
         */
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            avatarAdapter = new AvatarAdapter(context,mAvatarList, this);
            mRecyclerView.setAdapter(avatarAdapter);
            mRecyclerView.setLayoutManager(layoutManager);
        }, 200);

        /**
         * onClicking close button the dialog fragment dismisses
         */
        close.setOnClickListener(v -> dismiss());

        /**
         * onClicking cancel button the dialog fragment dismisses
         */
        cancel.setOnClickListener(v -> dismiss());

        /**
         * onClicking save button the username and avatar string corresponding to avatar image is saved to database
         */
        save.setOnClickListener(v -> {
            String name1 = String.valueOf(name.getText());
            String username = name1.trim();
            if(!username.isEmpty()&&avatar!=null){
                mainActivityInterface.addProfile(username , avatar);
                Toast.makeText(getContext(), "Profile Saved successfully", Toast.LENGTH_SHORT).show();
                dismiss();
            }else
                Toast.makeText(getContext(), "YOU CANNOT HAVE FIELDS EMPTY" , Toast.LENGTH_SHORT).show();

        });

        return rootView;
    }


    @Override
    public void onAvatarClick(String avatar) {
        this.avatar = avatar;

    }

}
