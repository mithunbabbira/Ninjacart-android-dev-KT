package com.example.fragmentdatatransfer;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment  {
    private FragmentBlistener listener;

    private EditText editText;
    private TextView disId ,disPass;
    private Button button;

    private boolean check = false;



    public  interface  FragmentBlistener{
        void onInputBSent(Bundle input);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_b,container,false);

        editText =view.findViewById(R.id.edit_text);

        disId=view.findViewById(R.id.dis_id);
        disPass=view.findViewById(R.id.dis_pass);
        button=view.findViewById(R.id.button_ok);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle res = new Bundle();
                if(check){
                    res.putString("vali","grant");

                }else{
                    res.putString("vali","deny");

                }

                listener.onInputBSent(res);


            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentBlistener){
            listener = (FragmentBlistener)context;
        }else{
            throw new RuntimeException(context.toString()+
                    " must implement FragmentBListener");
        }

    }


    public void updateEditTextB(Bundle res){
        disId.setText(res.getString("id"));
        disPass.setText(res.getString("pass"));
         check = res.getString("pass").equals("mithun")? true : false;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
