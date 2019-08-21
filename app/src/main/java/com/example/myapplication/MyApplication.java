package com.example.myapplication;

import android.app.Application;
import android.os.StrictMode;

import com.github.anrwatchdog.ANRWatchDog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()   // or .detectAll() for all detectable problems
//                .penaltyDialog() //弹出违规提示对话框
//                .penaltyLog() //在Logcat 中打印违规异常信息
//                .penaltyFlashScreen() //API等级11
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects() //API等级11
//                .penaltyLog()
//                .penaltyDeath()
//                .build());


        new ANRWatchDog().start();
    }
}
