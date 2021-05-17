package com.example.gffecfl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    TextView existingAccountText;
    EditText email, team, leader, member, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = this.findViewById(R.id.registerButton);
        existingAccountText = this.findViewById(R.id.existingAccountText);
        email = this.findViewById(R.id.editTextEmailRegister);
        team = this.findViewById(R.id.editTextTeamRegister);
        leader = this.findViewById(R.id.editTextTeamLeaderRegister);
        member = this.findViewById(R.id.editTextTeamMemberRegister);
        password = this.findViewById(R.id.editTextPasswordRegister);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        existingAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(intent);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getText().toString();
                String pass = password.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(username,pass)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,
                                            "Registration Successful",Toast.LENGTH_LONG).show();
                                    Intent intent =new Intent(RegisterActivity.this,HomeActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this,
                                            "Unsuccessful",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}


