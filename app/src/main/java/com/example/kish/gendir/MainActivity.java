package com.example.kish.gendir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;

public class MainActivity extends AppCompatActivity {
    private final String url = "";

    Worker worker = null;

    private TableLayout tableLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        worker = new CsvWorker(getResources().openRawResource(R.raw.banks),
                getResources().openRawResource(R.raw.payers),
                getResources().openRawResource(R.raw.recipients),
                getResources().openRawResource(R.raw.bills)
        );


        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayout.addView(new TextView(this));


        worker.getListPayment().forEach(payment -> {
            if ( payment.getStatus().equals("none") ){

            }
        });
    }
}
