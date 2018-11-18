package com.example.kish.gendir;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.Map;
import java.util.TreeMap;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kish.gendir.model.ContrAgent;
import com.example.kish.gendir.worker.CsvWorker;
import com.example.kish.gendir.worker.Worker;
import com.example.kish.gendir.worker.WorkerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings_gd extends AppCompatActivity {
    private final int IDD_CONTRAG = 10;
    private Worker worker;
    protected  MainActivity mainActivity = new MainActivity();

    public static Map<String, Boolean> permissions = new TreeMap<String, Boolean>(){{
            put("Name", false);
            put("Date", false);
            put("Name_ContAgent", false);
            put("Id_ContAgent", false);
            put("Name_Customer", false);
            put("Id_Customer", false);
            put("Id_Bank", false);
            put("Type", false);
            put("Summ", false);
            put("Status", false);
        }};


    public static Map<String, Object> dictionary = new TreeMap<String, Object>(){{
        put("Name", R.id.checkBox);
        put("Date", R.id.checkBox2);
        put("Name_ContAgent", R.id.checkBox3);
        put("Id_ContAgent", R.id.checkBox4);
        put("Name_Customer", R.id.checkBox5);
        put("Id_Customer", R.id.checkBox6);
        put("Id_Bank", R.id.checkBox7);
        put("Type", R.id.checkBox8);
        put("Summ", R.id.checkBox9);
        put("Status", R.id.checkBox10);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_gd);
        worker = WorkerFactory.getWorker();

        for(Map.Entry<String, Object>entry:dictionary.entrySet()){
            final CheckBox checkBox = findViewById((Integer) entry.getValue());
            checkBox.setChecked(permissions.get(entry.getKey()));

            checkBox.setOnClickListener(v->{
                permissions.put(entry.getKey(), checkBox.isChecked());
            });
        }

    }
    public void onClick_showPackage(View view) {
        Intent intent = new Intent(Settings_gd.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClick(View view) {
        //ad.show();
        showDialog(IDD_CONTRAG);
    }

    public String[]getUniqContAgent(List<com.example.kish.gendir.model.Payment>payments){
        Map<String, Integer> dictionary = new HashMap<>();
        payments.forEach(payment -> {
            if(dictionary.containsKey(payment.getContrAgent()))
                dictionary.put(payment.getContrAgent().getName(), dictionary.get(payment.getContrAgent().getName()) + 1);
            else
                dictionary.put(payment.getContrAgent().getName(), 1);
        });
        return  dictionary.keySet().toArray(new String[dictionary.keySet().size()]);
    }
    public String[]getUniqNames(List<com.example.kish.gendir.model.Payment>payments){
        Map<String, Integer>dictionary = new HashMap<String, Integer>();
        payments.forEach(payment -> {
            if(dictionary.containsKey(payment.getName()))
                dictionary.put(payment.getName(), dictionary.get(payment.getName()) + 1);
            else
                dictionary.put(payment.getName(), 1);
        });
        return  dictionary.keySet().toArray(new String[dictionary.keySet().size()]);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case IDD_CONTRAG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final boolean[] mCheckedItems = { false, true, false, false, false, false,false,false,false,false };
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Список контрагентов")
                        .setCancelable(false)

                        .setMultiChoiceItems(getUniqContAgent(worker.getListPayment()), mCheckedItems,
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
                        for (int i = 0; i < getUniqContAgent(worker.getListPayment()).length; i++) {
                            state.append("" + getUniqContAgent(worker.getListPayment())[i]);
                            if (mCheckedItems[i])
                                state.append(" выбран\n");
                           // else
                             //   state.append(" не выбран\n");
                        }
                        final TextView helloTextView = (TextView) findViewById(R.id.ed1);
                        helloTextView.setText(state);
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


}
