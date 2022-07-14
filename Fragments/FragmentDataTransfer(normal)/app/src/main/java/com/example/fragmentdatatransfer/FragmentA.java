package com.example.fragmentdatatransfer;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private FragmentAlistener listener;

    private EditText editText;
    private Button button;



    public  interface  FragmentAlistener{
        void onInputASent(CharSequence input);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_a,container,false);

        editText =view.findViewById(R.id.edit_text);
        button=view.findViewById(R.id.button_ok);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = editText.getText();
                listener.onInputASent(input);


            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentAlistener){
            listener = (FragmentAlistener)context;
        }else{
            throw new RuntimeException(context.toString()+
                    " must implement FragmenetAListener");
        }

    }


    public void updateEditText(CharSequence newText){
        editText.setText(newText);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
