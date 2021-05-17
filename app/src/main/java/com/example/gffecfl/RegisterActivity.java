package com.example.gffecfl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    TextView existingAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton=this.findViewById(R.id.registerButton);
        existingAccountText=this.findViewById(R.id.existingAccountText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        existingAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                RegisterActivity.this.startActivity(intent);

            }
        });
    }


}