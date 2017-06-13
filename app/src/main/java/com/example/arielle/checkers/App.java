package com.example.arielle.checkers;

import android.content.Context;

/**
 * Created by Connor on 2017-06-06.
 */

public class App extends android.app.Application {
    private static App mApp = null;

    /* (non-Javadoc)
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context context() {
        return mApp.getApplicationContext();
    }

    //* allows easier access to resource files
}
