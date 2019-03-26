package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalBindService extends Service {
    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
    return binder;
    }

    class MyBinder extends Binder{
        public Integer calculateFactorial(Integer number){
            return MathUtil.calculateFactorial(number);
        }

        public LocalBindService getetService() {
            return LocalBindService.this;
        }
    }
}

