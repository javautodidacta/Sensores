package es.javautodidacta.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.SENSOR_SERVICE;

public class SensoresFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.title_sensors)
    TextView titleSensors;

    private SensorAdapter mAdapter;
    private List<Sensor> mSensorList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        unbinder = ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUi();

        return v;
    }

    private void updateUi() {
        if (getActivity() != null) {
            SensorManager sm = (SensorManager) getActivity()
                    .getSystemService(SENSOR_SERVICE);
            if (sm != null) {
                mSensorList = sm.getSensorList(Sensor.TYPE_ALL);
            }
        }

        titleSensors.setText(getString(R.string.titulo_sensores, mSensorList.size()));

        if (mAdapter == null) {
            mAdapter = new SensorAdapter(mSensorList);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setSensores(mSensorList);
            mAdapter.notifyDataSetChanged();
        }
    }

    class SensorHolder extends RecyclerView.ViewHolder implements SensorEventListener {

        @BindView(R.id.sensor_name)
        TextView sensorName;
        @BindView(R.id.sensor_values)
        TextView sensorValues;

        SensorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_item, parent, false));
            unbinder = ButterKnife.bind(this, itemView);
        }

        void bind(Sensor sensor) {
            sensorName.setText(sensor.getName());
            if(getActivity() != null) {
                SensorManager sm = (SensorManager) getActivity()
                        .getSystemService(SENSOR_SERVICE);
                if(sm != null) {
                    // Delay in microseconds (1 second).
                    // It is a hint - it can go faster or lower.
                    sm.registerListener(this,
                            sensor, 1_000_000);
                }
            }
        }


        @Override
        public void onSensorChanged(SensorEvent event) {
            StringBuilder values = new StringBuilder();
            for (Sensor sensor : mSensorList) {
                if (event.sensor == sensor) {
                    int valuesNumber = event.values.length;
                    values.append(getString(R.string.values_number, valuesNumber))
                            .append("\n");
                    for (float value : event.values) {
                        values.append(value).append("\n");
                    }
                    sensorValues.setText(values.toString());
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    public class SensorAdapter
            extends RecyclerView.Adapter<SensorHolder> {
        private List<Sensor> mSensores;

        SensorAdapter(List<Sensor> sensors) {
            mSensores = sensors;
        }

        void setSensores(List<Sensor> sensors) {
            mSensores = sensors;
        }


        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SensorHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
            if (mSensores.size() > 0) {
                Sensor sensor = mSensores.get(position);
                holder.bind(sensor);
            }
        }

        @Override
        public int getItemCount() {
            return mSensores.size();
        }

    }
}
