package com.example.mollysqlitev2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mollysqlitev2.Database.DatabaseHelper;
import com.example.mollysqlitev2.R;

//Coding with Mitch: Save data into SQLite Database

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Add Items to Shopping List");
        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnViewData = findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        //Adding the item to the SQLite Database when the user clicks the "Add" button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    //if the text box is empty, nothing will ba added to the database and this message will appear
                    toastMessage("Please enter something in the text field");
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This intent brings the user to the ListDataActivity where the shopping list is displayed
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    //these 2 override methods were taken from the YouTube video Options Menu with Sub Items by Coding in Flow
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
               Intent intent = new Intent(MainActivity.this, MainActivity.class);
               startActivity(intent);
               return true;
           case R.id.faq:
              // Toast.makeText(this, "Sub Item 1 selectted", Toast.LENGTH_SHORT).show();
               Intent intent2 = new Intent(MainActivity.this, FaqActivity.class);
               startActivity(intent2);
               return true;
           case R.id.contact:
              // Toast.makeText(this, "Sub Item 2 selectted", Toast.LENGTH_SHORT).show();
               Intent intent3 = new Intent(MainActivity.this, ContactActivity.class);
               startActivity(intent3);
               return true;
           default: return super.onOptionsItemSelected(item);
       }


    }

    public void AddData(String newEntry) {
        //This method returns a toast. If the data was successfully saved to the database then the toast will tell the user it was successful, otherwise
        //they will be notified that something went wrong
       boolean insertData = mDatabaseHelper.addData(newEntry);
       if (insertData) {
           toastMessage("Item Added to Shopping List!");
       } else {
           toastMessage("Sorry, Please Try Again");
       }

    }



    //creating a customisable toast message
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
