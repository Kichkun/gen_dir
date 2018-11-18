package com.example.kish.gendir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PayPackage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_package);
    }
    public void onClick_showCurrent(View view) {
        Intent intent = new Intent(PayPackage.this, Payment.class);
        startActivity(intent);
    }
    public void onClick_Back(View view) {
        Intent intent = new Intent(PayPackage.this, MainActivity.class);
        startActivity(intent);
    }
}
