package com.example.gpslocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.location.Location;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imgbtn = (ImageButton) findViewById(R.id.button);
        TextView txt_lat = (TextView) findViewById(R.id.text1);
        TextView txt_lon = (TextView) findViewById(R.id.text2);
        TextView txt_alt = (TextView) findViewById(R.id.text3);
        TextView txt_time = (TextView) findViewById(R.id.text4);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 111);

        imgbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();

                if (l!=null)
                {
                    double Latitude =l.getLatitude();
                    double Longitude =l.getLongitude();
                    double  Altitude =l.getAltitude();
                    long Time =l.getTime();

                    txt_lat.setText("Latitude : " + Latitude);
                    txt_lon.setText("Longitude : " + Longitude);
                    txt_alt.setText("Altitude : " + Altitude);
                    txt_time.setText("Time : " + Time);
                }
            }
        });
    }
}
