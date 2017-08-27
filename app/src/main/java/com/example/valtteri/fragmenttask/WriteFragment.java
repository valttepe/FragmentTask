package com.example.valtteri.fragmenttask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class WriteFragment extends Fragment {

    private static final String Fname = "myFile.txt";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_write, container, false);
        final Button savebtn = v.findViewById(R.id.saveButton);
        savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveExternalStorage(v);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public void saveExternalStorage (View view) {

        final EditText entryData = (EditText)view.findViewById(R.id.writeText);
        String dataToSave = entryData.getText().toString();

        try {
            FileOutputStream fos = getActivity().openFileOutput(Fname, getActivity().MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(dataToSave);
            bw.flush();
            bw.close();
            osw.close();
            fos.close();
        }
        catch (Exception e) {
            Log.d("writeFile", e.toString());
        }
    }


}
