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

import com.example.gffecfl.Objects.Teams;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

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
                String username = email.getText().toString().trim();
                String pass = password.getText().toString();
                String team_lead = leader.getText().toString().trim();
                String team_member = member.getText().toString().trim();
                String team_name = team.getText().toString().trim();

                if(!username.equals("") && !pass.equals("") && !team_lead.equals("") && !team_name.equals("")) {
                    firebaseAuth.createUserWithEmailAndPassword(username, pass)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        addTeamToFirebase(firebaseAuth.getCurrentUser().getUid(), team_name, team_lead, team_member);
                                        Toast.makeText(RegisterActivity.this,
                                                "Registration Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                        RegisterActivity.this.startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this,
                                                "Unsuccessful", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Please enter values in all fields",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void addTeamToFirebase(String uid, String team_name, String team_lead, String team_member) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Teams");

        Map<String,String> map = new HashMap<>();
        map.put("Name",team_name);
        map.put("Leader",team_lead);
        map.put("Member",team_member);

        databaseReference.child(uid).setValue(map);
    }
}


