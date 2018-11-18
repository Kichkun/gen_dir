package com.example.kish.gendir;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.Map;
import java.util.TreeMap;

public class Settings_gd extends AppCompatActivity {
    private final int IDD_CONTRAG = 3;
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
    public void onClick(View v) {
        //ad.show();
        showDialog(IDD_CONTRAG);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        return this.mainActivity.myCustomOnClick(id);
    }
}
