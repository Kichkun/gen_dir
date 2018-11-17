package com.example.kish.gendir;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;

public class MainActivity extends AppCompatActivity {
    private final String url = "";

    Worker worker = null;

    private TableLayout tableLayout = null;


    AlertDialog.Builder ad;
    Context context;

    // идентификатор диалогового окна AlertDialog с кнопками
    private final int IDD_CONTRAG = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    public void onClick(View v) {
        //ad.show();
        showDialog(IDD_CONTRAG);
    }
    public Dialog myCustomOnClick(int id) {

        switch (id) {
            case IDD_CONTRAG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final boolean[] mCheckedItems = { false, true, false };
                final String[] checkCatsName = { "", "", "" };
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Имена контрагентов")
                        .setCancelable(false)

                        .setMultiChoiceItems(checkCatsName, mCheckedItems,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                        mCheckedItems[which] = isChecked;
                                    }
                                })

                        // Добавляем кнопки
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        StringBuilder state = new StringBuilder();
                                        for (int i = 0; i < checkCatsName.length; i++) {
                                            state.append("" + checkCatsName[i]);
                                            if (mCheckedItems[i])
                                                state.append(" выбран\n");
                                            else
                                                state.append(" не выбран\n");
                                        }
                                        Toast.makeText(getApplicationContext(),
                                                state.toString(), Toast.LENGTH_LONG)
                                                .show();
                                    }
                                })

                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();

                                    }
                                });
                return builder.create();
            default:
                return null;
        }
    }
