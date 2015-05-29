package com.example.jeremy.reader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


public class MainActivity extends Activity {

    ListView list1;
    ArrayAdapter<String> adapter;
    ArrayList<String> stringArray1 = new ArrayList<String>();

    ListView list2;
    ArrayAdapter<String> adapter2;
    ArrayList<String> stringArray2 = new ArrayList<String>();

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

        //evens.txt
        Context context = view.getContext();
        String filename = "evens.txt";
        //String string = "Hello world!";
       // FileOutputStream outputStream;

        File file = new File(context.getFilesDir(), filename);

        //File fout = new File("out.txt");
        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 2; i < 20; i += 2) {
            bw.write(i);
            bw.newLine();
        }

        bw.close();

        //odds.txt
        Context context2 = view.getContext();
        String filename2 = "odds.txt";
        //String string = "Hello world!";
        // FileOutputStream outputStream;

        File file2 = new File(context2.getFilesDir(), filename2);

        //File fout = new File("out.txt");
        FileOutputStream fos2 = new FileOutputStream(file2);

        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

        for (int j = 1; j < 19; j += 2) {
            bw2.write(j);
            bw2.newLine();
        }

        bw2.close();

        //try {
        //    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        //    outputStream.write();//string.getBytes());
        //    outputStream.close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}




    }

    public void handleLoadFilesClick(View view) throws IOException {

        //ArrayAdapter<String> adapter;
        Context context = view.getContext();
        //ListView list1 = (ListView) findViewById(R.id.list1);
        list1.setAdapter(null);

        String FilePath = context.getFilesDir() + "/" + "evens.txt";

        File evensFile = new File( FilePath );

        FileInputStream fis = new FileInputStream(evensFile);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        //ArrayList<String> stringArray1 = new ArrayList<String>();

        String line;
        while ((line = br.readLine()) != null) {
            // add to ur list here

            //list1.add(line);
            stringArray1.add(line);

            //for now, I'm just going to display it
            System.out.println(line);
        }
        //adapter = new CustomAdapter(getActivity(), R.layout.row, myStringArray1);
       // adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, stringArray1);


       list1.setAdapter(adapter);

        br.close();



        //odds.txt
        //ArrayAdapter<String> adapter2;
        Context context2 = view.getContext();
        //ListView list2 = (ListView) findViewById(R.id.list2);
        list2.setAdapter(null);

        String FilePath2 = context2.getFilesDir() + "/" + "odds.txt";

        File oddsFile = new File( FilePath2 );

        FileInputStream fis2 = new FileInputStream(oddsFile);

        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));

        //ArrayList<String> stringArray2 = new ArrayList<String>();

        String line2;
        while ((line2 = br2.readLine()) != null) {
            // add to ur list here

            //list1.add(line);
            stringArray2.add(line2);

            //for now, I'm just going to display it
            System.out.println(line2);
        }


        //adapter = new CustomAdapter(getActivity(), R.layout.row, myStringArray1);
        //adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, stringArray2);


        list2.setAdapter(adapter2);

        br2.close();



    }
}