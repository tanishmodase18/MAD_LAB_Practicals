package com.example.guicomponets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity
{
    int i=1, font=30;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView t= (TextView) findViewById(R.id.text1);
        Button b1= (Button) findViewById(R.id.button1);
        Button b2= (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                t.setTextSize(font);
                font += 5;
                if (font == 50)
                    font = 25;
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (i)
                {
                    case 1:
                        t.setTextColor(Color.RED);
                        break;
                    case 2:
                        t.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        t.setTextColor(Color.YELLOW);
                        break;
                    case 4:
                        t.setTextColor(Color.GREEN);
                        break;
                    case 5:
                        t.setTextColor(Color.CYAN);
                        break;
                    case 6:
                        t.setTextColor(Color.MAGENTA);
                        break;
                    case 7:
                        t.setTextColor(Color.BLACK);
                        i = 0;
                        break;
                }
                i++;
            }
        });
    }
}
