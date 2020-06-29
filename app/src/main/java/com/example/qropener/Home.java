package com.example.qropener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btn_myactivity(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void btn_signUp(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
