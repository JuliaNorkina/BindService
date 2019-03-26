package com.example.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNumber;
    private TextView tvResult;
    private LocalBindService bindService;
    private ServiceConnection serviceConnection;
    private boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber = findViewById(R.id.etNumber);
        tvResult = findViewById(R.id.tvResult);
        findViewById(R.id.bCalculate).setOnClickListener(this);

        serviceConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                bindService = ((LocalBindService.MyBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
        Intent intent = new Intent(this, LocalBindService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        if (!bound) return;
        Integer result = bindService.calculateFactorial(Integer.parseInt(etNumber.getText().toString()));
        tvResult.setText(String.valueOf(result));
        unbindService(serviceConnection);
        bound = false;
    }
}
