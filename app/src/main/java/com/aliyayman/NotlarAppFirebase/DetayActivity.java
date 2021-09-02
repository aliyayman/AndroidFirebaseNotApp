package com.aliyayman.NotlarAppFirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetayActivity extends AppCompatActivity {
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Toolbar toolbar;
    private Notlar not;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        editTextDers=findViewById(R.id.editTextDers);
        editTextNot1=findViewById(R.id.editTextNot1);
        editTextNot2=findViewById(R.id.editTextNot2);
        toolbar=findViewById(R.id.toolbar);



        not= (Notlar) getIntent().getSerializableExtra("nesne");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        editTextDers.setText(not.getDers_adi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));


        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar,"Silinsin mi?",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myRef.child(not.getNot_id()).removeValue();



                        startActivity(new Intent(DetayActivity.this,MainActivity.class));

                        finish();

                    }
                }).show();
                return true;
            case R.id.action_duzenle:
                String ders_adi=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();

                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbar,"Ders adi giriniz...",Snackbar.LENGTH_SHORT).show();

                    return false;
                }
                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbar,"1. Notunuzu giriniz...",Snackbar.LENGTH_SHORT).show();

                    return false;
                }
                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbar,"2. Notunuzu giriniz..",Snackbar.LENGTH_SHORT).show();

                    return false;
                }

                Map<String,Object> bilgiler=new HashMap<>();
                bilgiler.put("ders_adi",ders_adi);
                bilgiler.put("not1",Integer.parseInt(not1));
                bilgiler.put("not2",Integer.parseInt(not2));
                myRef.child(not.getNot_id()).updateChildren(bilgiler);




                startActivity(new Intent(DetayActivity.this,MainActivity.class));

                finish();

                return true;

            default:
                return false;
        }
    }
}