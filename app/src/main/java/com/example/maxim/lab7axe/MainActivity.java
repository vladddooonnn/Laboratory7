package com.example.maxim.lab7axe;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager SenMan;
    Sensor Sen;
    TextView t1,t2;
    Button b1;
    double x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SenMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sen = SenMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(reset);
    }
    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            t1.setText("");
            t2.setText("");
            x_max=y_max=0;
        }
    };
    double x_max = 0, y_max = 0, z_max = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0]; y = event.values[1]; z = event.values[2];
        if(x_max==0 && y_max==0 && z_max==0) {
            x_max = x;
            y_max = y;
            z_max = z;
        }
        if(x_max<x) {
            x_max = x;
        }
        if(y_max<y) {
            y_max = y;
        }
        if(z_max<z) {
            z_max = z;
        }
        t1.setText( x + " " + y + " " + z + " ");
        t2.setText( x_max + " " + y_max + " " + z_max);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    protected void onResume() {
        super.onResume();
        SenMan.registerListener(this, Sen, SensorManager.SENSOR_DELAY_NORMAL);
    }
}

