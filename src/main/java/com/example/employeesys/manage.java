package com.example.employeesys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class manage extends AppCompatActivity {

    EditText name, email, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        Toolbar n = findViewById(R.id.mybar);
        // n.setTitle("Add Employee");
        setSupportActionBar(n);

        name = (EditText) findViewById(R.id.nametext);
        email = (EditText) findViewById(R.id.emailtext);
        number = (EditText) findViewById(R.id.numbertext);
    }

    //add record to database
    public void addRecord(View view) {
        DbManager db = new DbManager(this);
        String res = db.insertEmployee(name.getText().toString(), email.getText().toString(), number.getText().toString());

        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        name.setText("");
        email.setText("");
        number.setText("");

        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}