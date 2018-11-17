package com.example.kish.gendir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;

import java.util.List;

public class Payment extends AppCompatActivity {
    private Worker worker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        worker = new CsvWorker(getResources().openRawResource(R.raw.banks),
                getResources().openRawResource(R.raw.payers),
                getResources().openRawResource(R.raw.recipients),
                getResources().openRawResource(R.raw.bills));
        List<com.example.kish.gendir.model.Payment> payments = worker.getListPayment();
        payments.get(0).getSumm();
    }
}
