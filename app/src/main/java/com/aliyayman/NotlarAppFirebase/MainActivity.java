package com.aliyayman.NotlarAppFirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Notlar> notlarList;
    private NotlarAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        toolbar=findViewById(R.id.toolbar);
        fab=findViewById(R.id.fab);

        toolbar.setTitle("Not uygulaması");
        setSupportActionBar(toolbar);

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("notlar");

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        notlarList=new ArrayList<>();

        adapter=new NotlarAdapter(this,notlarList);
        rv.setAdapter(adapter);
        tumNotlar();




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,NotKayitActivty.class));
            }
        });
    }
    public void tumNotlar(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double toplam=0;
                double ortalama=0;
                notlarList.clear();
                for(DataSnapshot d:snapshot.getChildren()){
                    Notlar not=d.getValue(Notlar.class);
                    not.setNot_id(d.getKey());
                    toplam+=((not.getNot1()+not.getNot2())/2);
                    ortalama=toplam/notlarList.size();

                    notlarList.add(not);

                }
                adapter.notifyDataSetChanged();
                toolbar.setSubtitle("Ortalama:"+ortalama);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}