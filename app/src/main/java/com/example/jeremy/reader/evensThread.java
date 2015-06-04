package com.example.jeremy.reader;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jeremy on 6/1/2015.
 */
public class evensThread implements Runnable {

    public List<String> stringArray1 = Collections.synchronizedList(new ArrayList<String>());
    //ArrayList<String> stringArray1 = new ArrayList<String>();
    BufferedReader br;
    public ArrayAdapter<String> adapter;
    ListView list1;

    evensThread( List<String> stringArray1,BufferedReader br, ArrayAdapter<String> adapter, ListView list1 ) {
        this.stringArray1 = stringArray1;
        this.br = br;
        this.adapter = adapter;
        this.list1 = list1;
    }



    @Override
    public void run(){

        //MainActivity ecounter = MainActivity();

        //ecounter.context = view.getContext();
      // list1.setAdapter(null);

     //  adapter.clear();



//
     // File evensFile = new File( FilePath );

     //  FileInputStream fis = null;
      // try {
       //     fis = new FileInputStream(evensFile);
      //  } catch (FileNotFoundException e) {
       //     e.printStackTrace();
      // }
        //BufferedReader br = new BufferedReader(new InputStreamReader(fis));



        String line;
        try {
            while ((line = br.readLine()) != null) {
                stringArray1.add(line);
                adapter.notifyDataSetChanged();

                list1.setAdapter(adapter);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  adapter.notifyDataSetChanged();

     //  list1.setAdapter(adapter);

     //  try {
      //     br.close();
      //  } catch (IOException e) {
      //      e.printStackTrace();
      //  }

    }
}
