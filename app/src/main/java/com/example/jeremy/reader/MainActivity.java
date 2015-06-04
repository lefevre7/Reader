package com.example.jeremy.reader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Collections;


public class MainActivity extends Activity {

    public ListView list1;
    public ArrayAdapter<String> adapter;
    public List<String> stringArray1 = Collections.synchronizedList(new ArrayList<String>());
    //public ArrayList<String> stringArray1 = new ArrayList<String>();

    public ListView list2;
    public ArrayAdapter<String> adapter2;
    public ArrayList<String> stringArray2 = new ArrayList<String>();

    public Context context;
    public Context context2;

    BufferedReader br;

    boolean terminate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = (ListView) findViewById(R.id.list1);
        list2 = (ListView) findViewById(R.id.list2);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, stringArray1);
        list1.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, stringArray2);
        list2.setAdapter(adapter2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handleCreateFilesClick(View view) throws IOException {

        System.out.println("Process is starting.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //evens.txt
        Context context = view.getContext();
        String filename = "evens.txt";

        File file = new File(context.getFilesDir(), filename);

        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 2; i < 22; i += 2) {
            String k = Integer.toString(i);
            bw.write(k);
            bw.newLine();
        }

        bw.close();

        //odds.txt
        Context context2 = view.getContext();
        String filename2 = "odds.txt";

        File file2 = new File(context2.getFilesDir(), filename2);

        FileOutputStream fos2 = new FileOutputStream(file2);

        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

        for (int j = 1; j < 21; j += 2) {
            String l = Integer.toString(j);

            bw2.write(l);
            bw2.newLine();
        }

        bw2.close();


        System.out.println("Process is finishing.");


    }

    public void handleLoadFilesClick(View view) throws IOException, InterruptedException {

        terminate = false;
        context = view.getContext();
        list1.setAdapter(null);

        adapter.clear();



        String FilePath = context.getFilesDir() + "/" + "evens.txt";

        File evensFile = new File( FilePath );

        FileInputStream fis = new FileInputStream(evensFile);

        br = new BufferedReader(new InputStreamReader(fis));

        ////Thread t1 = new Thread(new DoThread(shared));

       // Thread t = new Thread(new evensThread( stringArray1, br, adapter, list1));

        ////Thread t = new Thread(counter);
        //t.start();


        String line;
        while ((line = br.readLine()) != null && terminate == false) {
            stringArray1.add(line);
            adapter.notifyDataSetChanged();

            list1.setAdapter(adapter);

            Thread.sleep(100);
        }

        //adapter.notifyDataSetChanged();

        //list1.setAdapter(adapter);

        br.close();



        //odds.txt
        //ArrayAdapter<String> adapter2;
        Context context2 = view.getContext();
        //ListView list2 = (ListView) findViewById(R.id.list2);
        list2.setAdapter(null);
        adapter2.clear();

        String FilePath2 = context2.getFilesDir() + "/" + "odds.txt";

        File oddsFile = new File( FilePath2 );

        FileInputStream fis2 = new FileInputStream(oddsFile);

        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));

        //ArrayList<String> stringArray2 = new ArrayList<String>();

        String line2;
        while ((line2 = br2.readLine()) != null  && terminate == false) {
            // add to ur list here

            //list1.add(line);
            stringArray2.add(line2);
            adapter2.notifyDataSetChanged();

            list2.setAdapter(adapter2);

            Thread.sleep(300);

        }


        //adapter = new CustomAdapter(getActivity(), R.layout.row, myStringArray1);
        //adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, stringArray2);



        br2.close();

       // t.join();



    }

    public void handleStopLoadingFilesClick(View view) {
        terminate = true;

        list1.setAdapter(null);
        adapter.clear();
        adapter.notifyDataSetChanged();
        list1.setAdapter(adapter);

        list2.setAdapter(null);
        adapter2.clear();
        adapter2.notifyDataSetChanged();
        list2.setAdapter(adapter2);

    }
}