package com.example.qropener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {

    EditText editText;
    ImageButton button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(!text.equals("")){
                    new ImageDownloaderTask(imageView).execute("https://api.qrserver.com/v1/create-qr-code/?size=1000x1000&data=" + text);
                } else {
                    Toast.makeText(Signup_Form.this, "Enter Something!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
