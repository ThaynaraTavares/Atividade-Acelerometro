package com.example.exemploacelerometro;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    
    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;
    private TextView textViewDetail;
     
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
         
        textViewX = (TextView) findViewById(R.id.addToDictionary);
        textViewY = (TextView) findViewById(R.id.addToDictionary);
        textViewZ = (TextView) findViewById(R.id.addToDictionary);
        textViewDetail = (TextView) findViewById(R.id.addToDictionary);
         
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //Instância para SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         
    }
       
    @Override
    protected void onResume() { //iniciará o processo de captura.
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL); //Iniciar as capturas
    }
     
    @Override
    protected void onPause() { // Irá parar o onResume.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
     
    public void onAccuracyChanged1(Sensor sensor, int accuracy) { //Mudanças na precisão do sensor.
    }
 
    public void onSensorChanged1(SensorEvent event) { //Mudanças na posição do dispositivo. //Recebe SensorEvent
        Float x = event.values[0]; // Direita ou esquerda.
        Float y = event.values[1]; // Invertido ou normal.
        Float z = event.values[2]; //Para frente ou trás
         
       
        textViewX.setText("Posição X: " + x.intValue() + " Float: " + x);
        textViewY.setText("Posição Y: " + y.intValue() + " Float: " + y);
        textViewZ.setText("Posição Z: " + z.intValue() + " Float: " + z);
         
        if(y < 0) { // O dispositivo esta de cabeça pra baixo
            if(x > 0)  
                textViewDetail.setText("Virando para ESQUERDA ficando INVERTIDO");
            if(x < 0)  
                textViewDetail.setText("Virando para DIREITA ficando INVERTIDO");   
        } else {
            if(x > 0)  
                textViewDetail.setText("Virando para ESQUERDA ");
            if(x < 0)  
                textViewDetail.setText("Virando para DIREITA ");
        }   
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

}