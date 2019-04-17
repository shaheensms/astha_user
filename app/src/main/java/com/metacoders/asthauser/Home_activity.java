package com.metacoders.asthauser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home_activity extends AppCompatActivity {

    CardView histroy ;
    DatabaseReference mref ;
    FirebaseAuth mauth;
    String uid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mauth = FirebaseAuth.getInstance();




        histroy = findViewById(R.id.history_card);






        histroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , history.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();





    }
}
