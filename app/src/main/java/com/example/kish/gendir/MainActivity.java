package com.example.kish.gendir;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;
import com.example.kish.gendir.worker.WorkerFactory;

public class MainActivity extends AppCompatActivity {
    private final String url = "";
    private com.example.kish.gendir.model.Payment payments []= new com.example.kish.gendir.model.Payment[4];

    private Worker worker = null;
    private Context context;

    // идентификатор диалогового окна AlertDialog с кнопками

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worker = WorkerFactory.getWorker();


        EditText[]arrEditText = new EditText[4];
        arrEditText [0] = findViewById(R.id.push1);
        arrEditText [1] = findViewById(R.id.push);
        arrEditText [2] = findViewById(R.id.push2);
        arrEditText [3] = findViewById(R.id.push3);

        FloatingActionButton buttonsOk[] = new FloatingActionButton[4];
        buttonsOk[0] = findViewById(R.id.floatingActionButton5_);
        buttonsOk[1] = findViewById(R.id.floatingActionButton6);
        buttonsOk[2] = findViewById(R.id.floatingActionButton7);
        buttonsOk[3] = findViewById(R.id.floatingActionButton8);

        FloatingActionButton buttonsNo[] = new FloatingActionButton[4];
        buttonsNo[0] = findViewById(R.id.floatingActionButton19);
        buttonsNo[1] = findViewById(R.id.floatingActionButton20);
        buttonsNo[2] = findViewById(R.id.floatingActionButton21);
        buttonsNo[3] = findViewById(R.id.floatingActionButton22);

        for(int i = 0; i < buttonsOk.length; i++){
            final int offst = i;
            buttonsOk[offst].setOnClickListener((v) -> {
                com.example.kish.gendir.model.Payment payment = null;
                worker.drop(payments[offst]);
                while (worker.getListPayment().iterator().hasNext() && !worker.getListPayment().get(0).getStatus().equals("None"))
                    worker.drop(worker.getListPayment().get(0));
                payment = worker.getListPayment().get(0);
                if ( payment == null) {
                    arrEditText[offst].setText("");
                    payments[offst] = null;
                }
                else {
                    for(int j = offst; j < payments.length - 1; j++){
                        payments[j] = payments[j + 1];
                        arrEditText[j].setText(payments[j].getId() + ". " + payments[j].getName());
                    }
                    arrEditText[arrEditText.length - 1].setText(payment.getId() + ". " + payment.getName());

                    payments[arrEditText.length - 1] = payment;
                    worker.drop(payment);

                }
            });
        }
        for(int i = 0; i < buttonsNo.length; i++){
            final int offst = i;
            buttonsNo[offst].setOnClickListener((v) -> {
                com.example.kish.gendir.model.Payment payment = null;
                worker.deny(payments[offst]);
                while (worker.getListPayment().iterator().hasNext() && !worker.getListPayment().get(0).getStatus().equals("None"))
                    worker.deny(worker.getListPayment().get(0));
                payment = worker.getListPayment().get(0);
                if ( payment == null) {
                    arrEditText[offst].setText("");
                    payments[offst] = null;
                }
                else {
                    for(int j = offst; j < payments.length - 1; j++){
                        payments[j] = payments[j + 1];
                        arrEditText[j].setText(payments[j].getId() + ". " + payments[j].getName());
                    }
                    arrEditText[arrEditText.length - 1].setText(payment.getId() + ". " + payment.getName());

                    payments[arrEditText.length - 1] = payment;
                    worker.deny(payment);

                }
            });
        }
        for (int i = 0; i < arrEditText.length; i++) {
            com.example.kish.gendir.model.Payment payment = worker.getListPayment().get(0);
            payments[i] = payment;
            arrEditText[i].setText(payment.getId() + "." + payment.getName());
            worker.deny(payment);
        }
    }


    public void onClick_showPlat(View view) {
        Intent intent = new Intent(MainActivity.this, PayPackage.class);
        startActivity(intent);
    }
    public void onClick_showSettings(View view) {
        Intent intent = new Intent(MainActivity.this, Settings_gd.class);
        startActivity(intent);
    }


}
