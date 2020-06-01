package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText emailId;
    private EditText password;
    private Button signUP;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailId=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        signUP=findViewById(R.id.button);



        //Toast.makeText(SignUp.this, " eid "+eId+"pass"+pwd, Toast.LENGTH_SHORT).show();

        firebaseAuth=FirebaseAuth.getInstance();

        signUP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    String eId=emailId.getText().toString();
                    String pwd=password.getText().toString();

                    if(eId.isEmpty() && pwd.isEmpty()){
                        emailId.setError("Please enter valid emailId!");
                        password.setError("Please enter valid password!");
                        emailId.requestFocus();
                        password.requestFocus();
                    }
                    else if(eId.isEmpty()){
                        emailId.setError("Please enter valid emailId!");
                        emailId.requestFocus();
                    }
                    else if(pwd.isEmpty()){
                        password.setError("Please enter valid password!");
                        password.requestFocus();
                    }
                    else {
                        firebaseAuth.createUserWithEmailAndPassword(eId, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Successfully created user", Toast.LENGTH_SHORT).show();
                                 //   FirebaseUser user=firebaseAuth.getCurrentUser();

                                } else {
                                    Toast.makeText(SignUp.this, "SignUp Unsuccessfull, Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }catch (Exception e){
                    Toast.makeText(SignUp.this, "Unknown issue:"+e, Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }
}
