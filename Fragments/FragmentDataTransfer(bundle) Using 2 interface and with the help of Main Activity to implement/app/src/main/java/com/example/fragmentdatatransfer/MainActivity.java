package com.example.fragmentdatatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAlistener , FragmentB.FragmentBlistener {

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

    @Override
    public void onInputASent(Bundle input) {
        fragmentB.updateEditTextB(input);
    }

    @Override
    public void onInputBSent(Bundle input) {
        fragmentA.updateEditTextA(input);

    }
}