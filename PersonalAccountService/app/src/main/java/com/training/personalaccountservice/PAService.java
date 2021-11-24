/**
 * @file        PAService.java
 * @brief       PAService performs binding with client app.
 *
 * @author      Akshay K B
 */
package com.training.personalaccountservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @brief: PAService performs binding with client app.
 */
public class PAService extends Service {

    /**
     * Initialising context.
     */
    private Context context = this;

    /**
     * @brief: Performs service binding.
     * @param intent : Intent object received.
     * @return : Returns IBinder with functionalities defined in AIDL which is implemented in ServiceInterface.
     */
    @Override
    public IBinder onBind(Intent intent) {

        return new ServiceInterface(context);
    }
}