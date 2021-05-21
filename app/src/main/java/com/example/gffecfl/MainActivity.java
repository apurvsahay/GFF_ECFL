package com.example.gffecfl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView noAccountText;
    EditText email,pass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton= this.findViewById(R.id.cirLoginButton);
        noAccountText=this.findViewById(R.id.noAccountText);
        email=this.findViewById(R.id.editTextEmail);
        pass=this.findViewById(R.id.editTextPassword);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            MainActivity.this.startActivity(intent);
        }

        noAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=email.getText().toString();
                String password=pass.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(username,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                    MainActivity.this.startActivity(intent);
                                }
                                else {
                                    Toast.makeText(MainActivity.this,
                                            "Invalid credentials",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}