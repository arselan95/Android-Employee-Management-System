package com.example.employeesys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


//display a specific contact and its details
public class DisplayContact extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView phone;
    DbManager db;
    MainActivity m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        Toolbar t = findViewById(R.id.mybar);
        t.setTitle("Edit Employee");
        setSupportActionBar(t);

        name = (TextView) findViewById(R.id.editName);
        email = (TextView) findViewById(R.id.editEmail);
        phone = (TextView) findViewById(R.id.editPhone);

        db = new DbManager(this);

        //get value of id from MainActivity.java
        Bundle extras = getIntent().getExtras();
        String thisstring = extras.getString("id");
        int Value = Integer.parseInt(thisstring);

        if (Value > 0) {
            Cursor rs = db.getData(Value);
            rs.moveToFirst();

            String nam = rs.getString(rs.getColumnIndex(db.EMPLOYEE_COLUMN_NAME));
            String emai = rs.getString(rs.getColumnIndex(db.EMPLOYEE_COLUMN_EMAIL));
            String phon = rs.getString(rs.getColumnIndex(db.EMPLOYEE_COLUMN_NUMBER));

            if (!rs.isClosed()) {
                rs.close();
            }

            name.setText((CharSequence) nam);
            name.setFocusable(false);
            name.setClickable(false);

            email.setText((CharSequence) emai);
            email.setFocusable(false);
            email.setClickable(false);

            phone.setText((CharSequence) phon);
            phone.setFocusable(false);
            phone.setClickable(false);


        }
    }

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

    //remove an employee
    public void remove(View view) {
        db = new DbManager(this);

        //alert dialog for the confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this contact ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //get that unique id and delete record
                Bundle extras = getIntent().getExtras();
                String s = extras.getString("id");
                int Val = Integer.parseInt(s);
                System.out.println(Val);
                db.removeEmployee(Val);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                Intent intent;
                intent = new Intent(DisplayContact.this, MainActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //cancel
            }
        });

        AlertDialog d = builder.create();
        d.setTitle("Are you sure");
        d.show();

    }


}
