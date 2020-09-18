package com.example.employeesys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    DbManager db;
    ArrayAdapter adi;
    ListView details;
    String id;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //my custom bar
        Toolbar t = findViewById(R.id.mybar);
        t.setTitle("Employee System");
        setSupportActionBar(t);

        db = new DbManager(this);

        //display employees
        display();

        details.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                id = db.arrid[arg2];
                System.out.println(id);
                Intent intent = new Intent(MainActivity.this, DisplayContact.class);

                //pass the value of id to DisplayContact.java
                Bundle extras = new Bundle();
                extras.putString("id", id + "");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }


    // custom bar navigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent;
                intent = new Intent(this, manage.class);
                startActivity(intent);
                break;

            case R.id.home_settings:
                Intent inten;
                inten = new Intent(this, MainActivity.class);
                startActivity(inten);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //display list of employee names
    public void display() {
        db.getAll();
        adi = new ArrayAdapter(this, android.R.layout.simple_list_item_1, db.arrname);
        for (int i = 0; i < db.arrname.length; i++) {
            System.out.println(db.arrname[i]);
        }
        details = findViewById(R.id.listView1);
        details.setAdapter(adi);
    }

    //add an employee
    public void manageEmployee(View view) {
        //new DbManager (this);
        Intent intent;
        intent = new Intent(this, manage.class);
        startActivity(intent);
    }


}