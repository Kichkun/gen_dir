package com.example.kish.gendir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;
import com.example.kish.gendir.worker.WorkerFactory;

import java.util.List;

public class Payment extends AppCompatActivity {
    private Worker worker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        worker = WorkerFactory.getWorker();
        List<com.example.kish.gendir.model.Payment> payments = worker.getListPayment();
        payments.get(0).getSumm();
    }
    public void onClick_PayBack(View view) {
        Intent intent = new Intent(Payment.this, PayPackage.class);
        startActivity(intent);
    }
}
