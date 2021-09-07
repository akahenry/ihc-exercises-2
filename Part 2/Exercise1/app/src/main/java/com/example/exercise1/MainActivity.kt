package com.example.exercise1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor
    private lateinit var mLightSensor: Sensor
    private lateinit var mPressureSensor: Sensor
    private lateinit var mButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        this.mAccelerometer =
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also { accelerometer ->
                mSensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_FASTEST,
                    SensorManager.SENSOR_DELAY_FASTEST
                )
            }

        this.mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT).also { lightsensor ->
            mSensorManager.registerListener(
                this,
                lightsensor,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        this.mPressureSensor =
            mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE).also { pressuresensor ->
                mSensorManager.registerListener(
                    this,
                    pressuresensor,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }

        this.mButton = findViewById(R.id.buttonLocation)
        this.mButton.setOnClickListener {
            val gps = GPSTracker(this)

            val location: Location? = gps.getLocation()

            if (location != null) {
                val titleLongitude: TextView = findViewById(R.id.textViewTitleLongitude)
                val editTextLongitude: EditText = findViewById(R.id.editTexLongitude)
                val titleLatitude: TextView = findViewById(R.id.textViewTitleLatitude)
                val editTextLatitude: EditText = findViewById(R.id.editTextLatitude)

                titleLongitude.isEnabled = true
                editTextLongitude.isEnabled = true
                titleLatitude.isEnabled = true
                editTextLatitude.isEnabled = true

                editTextLatitude.setText(location.latitude.toString())
                editTextLongitude.setText(location.longitude.toString())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        this.mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also{ accelerometer ->
            mSensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        this.mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT).also { lightsensor ->
            mSensorManager.registerListener(
                this,
                lightsensor,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        this.mPressureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE).also { pressuresensor ->
            mSensorManager.registerListener(
                this,
                pressuresensor,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val mSensorX: Float = event.values[0]
                val mSensorY: Float = event.values[1]
                val mSensorZ: Float = event.values[2]

                val mEditTextX: EditText = findViewById(R.id.editTextX)
                val mEditTextY: EditText = findViewById(R.id.editTextY)
                val mEditTextZ: EditText = findViewById(R.id.editTextZ)

                mEditTextX.setText(mSensorX.toString())
                mEditTextY.setText(mSensorY.toString())
                mEditTextZ.setText(mSensorZ.toString())

                return
            }
            Sensor.TYPE_LIGHT -> {
                val mSensor: Float = event.values[0]

                val mEditTextLight: EditText = findViewById(R.id.editTextLight)

                mEditTextLight.setText(mSensor.toString())

                return
            }
            Sensor.TYPE_PRESSURE -> {
                val mSensor: Float = event.values[0]

                val mEditTextPressure: EditText = findViewById(R.id.editTextPressure)

                mEditTextPressure.setText(mSensor.toString())

                return
            }
        }
    }
}
