package com.example.fragmentdatatransfer;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class FragmentA extends Fragment implements Fragmentlistener {
    public Fragmentlistener listener;
    private FragmentA fragmentA;

    private EditText editText , editPass;
    private TextView textView ;
    private Button button;








    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        editText =view.findViewById(R.id.edit_text);
        editPass =view.findViewById(R.id.edit_pass);
        button=view.findViewById(R.id.button_ok);
        textView = view.findViewById(R.id.result_View);


        ((MainActivity) requireActivity()).onSetFragmentBListener(new Fragmentlistener() {
            @Override
            public void onInputSent(Bundle res) {
                Log.d("12", "updateEditText: coming");
                textView.setText(res.getString("vali"));

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = editText.getText().toString();
                String inputPass = editPass.getText().toString();
                Bundle in = new Bundle();
                in.putString("id",inputText);
                in.putString("pass",inputPass);

                listener.onInputSent(in);

            }
        });









        return view;
    }

    public void updateEditTextA(Bundle res){
        Log.d("12", "updateEditText: coming");
        textView.setText(res.getString("vali"));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onInputSent(Bundle input) {

    }
}
