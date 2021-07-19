package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    ConstraintLayout mylayout;
    EditText Rollno, Name, Marks;
    Button Insert,Delete,Update,View,ViewAll;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mylayout = findViewById(R.id.database);
        Rollno = findViewById(R.id.rollno);
        Name = findViewById(R.id.name);
        Marks = findViewById(R.id.marks);
        Insert = (Button) findViewById(R.id.insert);
        Delete = (Button) findViewById(R.id.delete);
        Update = (Button) findViewById(R.id.update);
        View = (Button) findViewById(R.id.view);
        ViewAll = (Button) findViewById(R.id.viewall);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        db =openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    @SuppressLint("ShowToast")
    public void onClick(View view)
    {
        if (view==Insert)
        {
            if (Rollno.getText().toString().trim().length()==0 || Name.getText().toString().trim().length()==0 || Marks.getText().toString().trim().length()==0)
            {
                create_snackbar("Please Enter all Values");
                return;
            }
            else
            {
                db.execSQL("INSERT INTO student VALUES('"+Rollno.getText()+"','"+Name.getText()+"','"+Marks.getText()+"');");

                Snackbar.make(mylayout, "Record Added Successfully", Snackbar.LENGTH_LONG).setAction("View All", v -> {
                    Cursor c=db.rawQuery("SELECT * FROM student", null);
                    Intent intent =new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                }).show();
                clear_Values();
            }
        }

        if (view==Delete)
        {
            if (Rollno.getText().toString().trim().length()==0)
            {
                create_snackbar("Please Enter Roll No");
                return;
            }
            else
            {
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='"+Rollno.getText()+"'");
                    create_snackbar("Record Deleted Successfully");
                }
                else
                {
                    create_snackbar("Invalid Roll No");
                }
                clear_Values();
            }
        }

        if (view==Update)
        {
            if (Rollno.getText().toString().trim().length()==0)
            {
                create_snackbar("Please Enter Roll No");
                return;
            }
            else
            {
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE student SET name='" + Name.getText() + "',marks='" + Marks.getText() + "' WHERE rollno='"+Rollno.getText()+"'");
                    create_snackbar("Record Updated Successfully");
                }
                else
                {
                    create_snackbar("Invalid Roll No");
                }
                clear_Values();
            }
        }

        if (view==View)
        {
            if (Rollno.getText().toString().trim().length()==0)
            {
                create_snackbar("Please Enter Roll No");
                return;
            }
            else
            {
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'", null);
                if(c.moveToFirst())
                {
                    Name.setText(c.getString(1));
                    Marks.setText(c.getString(2));
                }
                else
                {
                    create_snackbar("Invalid Roll No");
                    clear_Values();
                }
            }
        }

        if (view==ViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM student", null);
            if (c.getCount()==0)
            {
                create_snackbar("No Records Found");
            }
            else
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        }
    }

    public void create_snackbar(String msg)
    {
        Snackbar.make(mylayout, msg, Snackbar.LENGTH_LONG).show();
    }

    public void clear_Values()
    {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Rollno.requestFocus();
    }
}
