package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    private Button logOut;
    private TextView textView;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logOut=findViewById(R.id.button2);

        firebaseAuth=FirebaseAuth.getInstance();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(HomePage.this,""+firebaseAuth.getCurrentUser(),Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();

                //Toast.makeText(HomePage.this,""+firebaseAuth.getCurrentUser(),Toast.LENGTH_SHORT).show();
                Intent mainActivity=new Intent(HomePage.this,MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
        @Override
        public  void onStart(){
            super.onStart();
           // textView=findViewById(R.id.textView3);
           // textView.setText(firebaseAuth.getCurrentUser().getDisplayName());
        //   Toast.makeText(HomePage.this,"usarnam :"+firebaseAuth.getCurrentUser().isEmailVerified(),Toast.LENGTH_SHORT).show();

        }


}
