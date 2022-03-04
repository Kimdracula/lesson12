package com.example.realtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String PATH = "notes";
    public static final String KEY = "key";
    private SharedPreferences sharedPref;
    DatabaseReference myRef;
    ArrayList<UserNote> userNotes;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        if (sharedPref!=null){
        UserNote.setCounter(sharedPref.getInt(KEY,0));}

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef  = database.getReference(PATH);
        userNotes = new ArrayList<>();
        notesAdapter = new NotesAdapter(userNotes);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(notesAdapter);



        findViewById(R.id.fab).setOnClickListener(view -> {
UserNote generatedNote = new UserNote("New note "+UserNote.getNumOfInstances(),
        new Date(), "New type "+UserNote.getNumOfInstances());
            myRef.push().setValue(generatedNote);
            userNotes.add(generatedNote);
            notesAdapter.setNewData(userNotes);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()&& userNotes.size()==0) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        UserNote downloadedNote = postSnapshot.getValue(UserNote.class);
                        userNotes.add(downloadedNote);
                    }
                    notesAdapter.setNewData(userNotes); }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Ошибка БД",Toast.LENGTH_LONG).show(); }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(KEY,UserNote.getNumOfInstances()).apply();
    }
}
