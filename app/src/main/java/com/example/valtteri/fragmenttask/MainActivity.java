package com.example.valtteri.fragmenttask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            WriteFragment writeFrag = new WriteFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, writeFrag).commit();
        }

        final Button writebtn = (Button)findViewById(R.id.writeFragment);
        writebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangeFragment(v);
            }
        });

        final Button readbtn = (Button)findViewById(R.id.readFragment);
        readbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangeFragment(v);
            }
        });

    }

    public void ChangeFragment(View view) {
        Fragment fragment;

        if ( view == findViewById(R.id.readFragment)){
            fragment = new ReadFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);

            ft.commit();
        }
        else if (view == findViewById(R.id.writeFragment)) {
            fragment = new WriteFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
