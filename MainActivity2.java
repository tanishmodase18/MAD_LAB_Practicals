package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity
{
    TextView Dataset;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Dataset = findViewById(R.id.dataset);

        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");

        Cursor c=db.rawQuery("SELECT * FROM student", null);

        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext())
        {
            buffer.append("\t\t"+c.getString(0)+"\n");
            buffer.append("\t\t"+c.getString(1)+"\n");
            buffer.append("\t\t"+c.getString(2)+"\n\n");
        }
        Dataset.append(buffer.toString());
    }
}
