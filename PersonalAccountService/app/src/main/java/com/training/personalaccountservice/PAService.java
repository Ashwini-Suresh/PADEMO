package com.training.personalaccountservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PAService extends Service {
    public PAService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceImp();
    }
}