package com.example.mollysqlitev2.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mollysqlitev2.Database.DatabaseHelper;
import com.example.mollysqlitev2.Database.Models.Shopping;
import com.example.mollysqlitev2.Database.ShoppingAdapter;
import com.example.mollysqlitev2.R;

import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private ShoppingAdapter mAdapter;
    private List<Shopping> notesList = new ArrayList<>();
    private RecyclerView recyclerView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        setTitle("My Shopping List");

        recyclerView = findViewById(R.id.recycler_view);

        db = new DatabaseHelper(this);

        notesList.addAll(db.getAllNotes());

        mAdapter = new ShoppingAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                // Toast.makeText(this, "Item 1 selectted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.faq:
                // Toast.makeText(this, "Sub Item 1 selectted", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(ListDataActivity.this, FaqActivity.class);
                startActivity(intent2);
                return true;
            case R.id.contact:
                // Toast.makeText(this, "Sub Item 2 selectted", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(ListDataActivity.this, ContactActivity.class);
                startActivity(intent3);
                return true;
            default: return super.onOptionsItemSelected(item);
        }


    }

  //  private void createNote(String note) {
        // inserting note in db and getting
        // newly inserted note id
     //   long id = db.insertNote(note);

        // get the newly inserted note from db
     //   Shopping n = db.getNote(id);

     //   if (n != null) {
            // adding new note to array list at 0 position
     //       notesList.add(0, n);

            // refreshing the list
     //       mAdapter.notifyDataSetChanged();

           // toggleEmptyNotes();
  //      }
  //  }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
