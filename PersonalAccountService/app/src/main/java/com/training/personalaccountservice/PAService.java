package com.training.personalaccountservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class PAService extends Service {
    private Context context = this;

    @Override
    public IBinder onBind(Intent intent) {

        return new ServiceInterface(context);
    }
}