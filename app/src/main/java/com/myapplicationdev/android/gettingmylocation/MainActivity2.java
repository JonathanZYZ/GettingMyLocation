package com.myapplicationdev.android.gettingmylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    Button btnRefresh;
    ArrayList<String> al;
    ArrayAdapter<String> adapter;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.lv);
        textView2 = findViewById(R.id.textView2);
        al = new ArrayList<>();

//        al.add("TEST");
//        al.add("TEST1");
//        al.add("TEST2");
//        al.add("TEST3");

        btnRefresh = findViewById(R.id.btnRefresh);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);
        check();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.clear();
                check();
            }
        });
    }
    private void check(){
        String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
        File targetFile = new File(folderLocation, "data.txt");
        if (targetFile.exists() == true){
            String data ="";
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null){
                    data += line + "\n";
                    al.add(line);
                    line = br.readLine();
                    //al.add(line);
                }
                textView2.setText(" Number of records: "+al.size());
                br.close();
                reader.close();
            } catch (Exception e) {
                Toast.makeText(MainActivity2.this, "Failed to read!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Log.d("Content", data);
            adapter.notifyDataSetChanged();
        }
    }
}