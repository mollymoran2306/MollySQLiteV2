package com.example.mollysqlitev2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    public void AddData(String newEntry) {
        //This method returns a toast. If the data was successfully saved to the database then the toast will tell the user it was successful, otherwise
        //they will be notified that something went wrong
       boolean insertData = mDatabaseHelper.addData(newEntry);
       if (insertData) {
           toastMessage("Data Successfully Inserted!");
       } else {
           toastMessage("Sorry, Something went Wrong");
       }

    }



    //creating a customisable toast message
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
