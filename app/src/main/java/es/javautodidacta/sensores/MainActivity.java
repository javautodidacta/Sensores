/*
package es.javautodidacta.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private List<Sensor> listaSensores;
    private TextView aTextView[][] = new TextView[20][3];
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout raiz = findViewById(R.id.raiz);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        listaSensores = sm.getSensorList(Sensor.TYPE_ALL);
        int n = 0;

        for (Sensor sensor : listaSensores) {
            TextView textView = new TextView(this);
            textView.setText(sensor.getName());
            raiz.addView(textView);
            LinearLayout linearLayout = new LinearLayout(this);
            raiz.addView(linearLayout);
            for (int i = 0; i < 3; i++) {
                aTextView[n][i] = new TextView(this);
                aTextView[n][i].setText("?");
                aTextView[n][i].setWidth(87);
            }

            TextView x = new TextView(this);
            x.setText(" X: ");
            linearLayout.addView(x);
            linearLayout.addView(aTextView[n][0]);
            TextView y = new TextView(this);
            y.setText(" Y: ");
            linearLayout.addView(y);
            linearLayout.addView(aTextView[n][1]);
            TextView z = new TextView(this);
            z.setText(" Z: ");
            linearLayout.addView(z);
            linearLayout.addView(aTextView[n][2]);

            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            n++;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            Log.i(TAG, "Lista sensores: " + listaSensores.size());
            Log.i(TAG, "Event values length: " + event.values.length);
            for (Sensor sensor : listaSensores) {
                if (event.sensor == sensor)
                    for (int n = 0; n < listaSensores.size(); n++) {

                    }
                    for (int i = 0; i < event.values.length
                            || i < aTextView[n].length; i++) {

                            try {
                                if(aTextView[n][i] != null) {
                                    aTextView[n][i].setText(
                                            Float.toString(event.values[i]));
                                }
                            } catch(ArrayIndexOutOfBoundsException exc) {
                                exc.printStackTrace();
                            }
                    }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
*/
