package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Button world = (Button)findViewById(R.id.button);
        Button india = (Button)findViewById(R.id.button2);
        Button science = (Button)findViewById(R.id.button3);
        Button cricket = (Button)findViewById(R.id.button4);

        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choose.this, World.class);
                startActivity(i);
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choose.this, India.class);
                startActivity(i);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choose.this, Science.class);
                startActivity(i);
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choose.this, Cricket.class);
                startActivity(i);
            }
        });
    }
}
