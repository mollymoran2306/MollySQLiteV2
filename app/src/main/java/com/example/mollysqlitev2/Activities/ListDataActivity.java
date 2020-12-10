package com.example.mollysqlitev2.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

        recyclerView.setAdapter(mAdapter);

    }

    private void createNote(String note) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(note);

        // get the newly inserted note from db
        Shopping n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

           // toggleEmptyNotes();
        }
    }



//    private void populateListView() {
//        Log.d(TAG, "populateListView: Displaying data in the ListView");
//
//
//        Cursor data = mDatabaseHelper.getData();
//        ArrayList<String> listData = new ArrayList<>();
//        //creating a loop that will loop through the database and add the values to an array
//        while(data.moveToNext()) {
//            listData.add(data.getString(1));
//        }
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        //setting the adapter to the listview on the layout, to display the values from the shopping list
//        mListView.setAdapter(adapter);
//    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
