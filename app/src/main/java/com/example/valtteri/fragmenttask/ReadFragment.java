package com.example.valtteri.fragmenttask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Valtteri on 27/08/2017.
 */

public class ReadFragment extends Fragment {

    private static final String Fname = "File.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        TextView tv = (TextView)view.findViewById(R.id.read_text);

        try {
            String content;
            InputStreamReader is = new InputStreamReader(getActivity().openFileInput(Fname));
            BufferedReader br = new BufferedReader(is);

            String line = "";

            while ((content = br.readLine()) != null) {
                line += content + "\n";
            }

            tv.setText(line);
            br.close();
            is.close();

        } catch (Exception pokemon) {
            Log.i("ReadFile", pokemon.toString());
        }


        return view;
    }
}
