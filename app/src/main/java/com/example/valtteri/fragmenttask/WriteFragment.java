package com.example.valtteri.fragmenttask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_APPEND;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class WriteFragment extends Fragment {

    private static final String Fname = "File.txt";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_write, container, false);

        Button savebtn = (Button)v.findViewById(R.id.saveButton);
        final EditText addtext = (EditText)v.findViewById(R.id.writeText);
        savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(addtext.getText().toString().isEmpty()) {
                    Snackbar.make(v, "No text", Snackbar.LENGTH_SHORT)
                            .show();
                }
                else{
                    try {
                        FileOutputStream fos = getActivity().openFileOutput(Fname, MODE_APPEND);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        BufferedWriter bw = new BufferedWriter(osw);
                        bw.write(addtext.getText().toString() + '\n');
                        bw.flush();
                        bw.close();
                        osw.close();
                        fos.close();
                    }
                    catch (Exception e) {
                        Log.d("writeFile", e.toString());
                    }

                    Snackbar.make(v, "File has been saved.", Snackbar.LENGTH_SHORT)
                            .show();

                    hideKeyboard();
                }

                Log.i("JTN", addtext.getText().toString());

            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
