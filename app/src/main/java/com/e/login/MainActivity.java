package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailId,password;
    private TextView signUP;
    private Button logIn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth =FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        logIn=findViewById(R.id.button);
        signUP =findViewById(R.id.textView);

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String eId=emailId.getText().toString();
                String pwd=password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(eId,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                            if(firebaseUser.isEmailVerified()) {
                                Intent homePage = new Intent(MainActivity.this, HomePage.class);
                                startActivity(homePage);
                            }else{
                                Toast.makeText(MainActivity.this,"Please verify your email id to login!",Toast.LENGTH_SHORT).show();
                                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                          Toast.makeText(MainActivity.this,"A verifying mail is sent to your email id!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    public  void  setSignUP(View v){
       Intent signUP=new Intent(MainActivity.this,SignUp.class);
       startActivity(signUP);
    }

    @Override
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //
        // updateUI(currentUser);
    }

}
