package com.aliyayman.NotlarAppFirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotKayitActivty extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers;
    private EditText editTextNot1;
    private EditText editTextNot2;
    private Button buttonKaydet;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit_activty);

        editTextDers=findViewById(R.id.editTextDers);
        editTextNot1=findViewById(R.id.editTextNot1);
        editTextNot2=findViewById(R.id.editTextNot2);
        buttonKaydet=findViewById(R.id.buttonKaydet);
        toolbar=findViewById(R.id.toolbar);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        toolbar.setTitle("Not Kayıt");
        setSupportActionBar(toolbar);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ders_adi=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();

                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(v,"Ders adi giriniz...",Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty((not1))){
                    Snackbar.make(v,"1. Notunuzu giriniz...",Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty((not2))){
                    Snackbar.make(v,"2. Notunuzu giriniz..",Snackbar.LENGTH_SHORT).show();

                    return;
                }



                Notlar not=new Notlar("",ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                myRef.push().setValue(not);

                startActivity(new Intent(NotKayitActivty.this,MainActivity.class));

                finish();
            }
        });

    }
}