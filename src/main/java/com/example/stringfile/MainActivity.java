package com.example.stringfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.stringfile.adapter.MyRecycleViewAdapter;
import com.example.stringfile.storage.FileStorage;
import com.example.stringfile.storage.NoteStorage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteStorage.setFileStorage(new FileStorage(this));
        NoteStorage.saveNotesToFile();

        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter();
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Intent createHeadline = new Intent(this, Headline.class);
                startActivity(createHeadline);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NoteStorage.readNotesFromFile();
        adapter.notifyDataSetChanged();
    }


}
