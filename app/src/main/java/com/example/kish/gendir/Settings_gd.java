package com.example.kish.gendir;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Settings_gd extends AppCompatActivity {
    private final int IDD_CONTRAG = 3;
    protected  MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_gd);
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
