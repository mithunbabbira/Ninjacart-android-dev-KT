package com.example.fragmentdatatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a,fragmentA)
                .replace(R.id.container_b,fragmentB)
                .commit();
    }
    public  void onSetFragmentAListener(Fragmentlistener fragmentlistener){
        fragmentA.listener =  fragmentlistener;
    }
    public  void onSetFragmentBListener(Fragmentlistener fragmentlistener){
        fragmentB.listener =  fragmentlistener;

    }



}