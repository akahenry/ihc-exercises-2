package com.example.exercise3

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.math.roundToInt




class MainActivity() : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        this.mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also{ accelerometer ->
            mSensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
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
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val mSensorX: Float = event.values[0]
            val mSensorY: Float = event.values[1]
            val mSensorZ: Float = event.values[2]

            var mEditTextX: EditText = findViewById(R.id.editTextX)
            var mEditTextY: EditText = findViewById(R.id.editTextY)
            var mEditTextZ: EditText = findViewById(R.id.editTextZ)

            mEditTextX.setText(mSensorX.toString())
            mEditTextY.setText(mSensorY.toString())
            mEditTextZ.setText(mSensorZ.toString())


            if (abs(mSensorX).roundToInt() == 10) {
                var secondActivity = Intent(this, MainActivity2::class.java)
                startActivity(secondActivity)
            }
        }
    }

}
