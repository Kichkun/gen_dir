package com.example.kish.gendir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;
import com.example.kish.gendir.worker.WorkerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PayPackage extends AppCompatActivity {
    private TableRow tableRow [] = new TableRow[5];
    private Worker worker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_package);
        worker = WorkerFactory.getWorker();
        initTable();


    }
    public void onClick_showCurrent(View view) {
        Intent intent = new Intent(PayPackage.this, Payment.class);
        startActivity(intent);
    }


    public void initTable(){

        TextView header = findViewById(R.id.id3);
        tableRow[0] = findViewById(R.id.a1);
        tableRow[1] = findViewById(R.id.a2);
        tableRow[2] = findViewById(R.id.a3);
        tableRow[3] = findViewById(R.id.a4);
        tableRow[4] = findViewById(R.id.a5);

        List<Map<String, Object>>table = worker.toTable();
        table = filerData(table, Settings_gd.permissions);
        String result = "";
        for(Map.Entry<String,Object>entry:table.get(0).entrySet()){
            result += entry.getKey() + "|";
        }
        header.setText(result);

        for (int i = 0; i < tableRow.length; i++) {



            TextView textView = new TextView(this);
            result = "|";

            for(Map.Entry<String, Object>entry:table.get(i).entrySet())
                result += entry.getValue() + "|";
            textView.setText(result);
            tableRow[i].addView(textView);
        }
    }

    private List<Map<String, Object>>filerData(List<Map<String, Object>>table, Map<String, Boolean>permissions) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        table.forEach(map -> {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            int j = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (permissions.get(entry.getKey()))
                    resultMap.put(entry.getKey(), entry.getValue());
                j++;
            }
            results.add(resultMap);
        });
        return results;
    }

    public void onClick_Back(View view) {
        Intent intent = new Intent(PayPackage.this, MainActivity.class);
        startActivity(intent);

    }
}
