package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private View aa;
    private ImageView imageView;
    private ImageView imageView1;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(0xffffff);
        }

        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.getClass().getSimpleName());

        Logger.d("yanghua", "这个log很方便  %s", 5);


        aa = findViewById(R.id.myview);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView2);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//================读文件流=======================================//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        readFile();
//                    }
//                }).start();

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/html"); //分享的是文本类型
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://wwww.baidu.com");//分享出去的内容
                startActivity(shareIntent);    //注意这里的变化


                //================View的刷新=======================================//
//                aa.setBackgroundResource(R.color.colorPrimaryDark);
//                aa.requestLayout();
//                aa.invalidate();

            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //=====================Activity跳转==================================//
                startActivity(new Intent(MainActivity.this, Hwvideo.class));
            }
        });


        EventBus.getDefault().register(this);

//        Glide.with(imageView).load(R.drawable.checked).into(imageView);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.checked);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.checked);

        imageView.setImageBitmap(bitmap1);
        imageView1.setImageBitmap(bitmap2);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

    public static class MessageEvent { /* Additional fields if needed */
    }

    private void readFile() {

        String path = "/data/data/com.example.myapplication";

        Log.d("yanghua", "path = " + path + "/server.log");
        File f1 = new File(path + "/server.log");
        if (f1.exists()) {

            Log.d("yanghua", "the path is OK");
        }
        else{
            try {
                f1.createNewFile();
            } catch (IOException e) {
                Log.d("yanghua", "create file error");
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f1);
            byte[] bytes = new byte[1024];
            //得到实际读取的长度
            int n = 0;
            //循环读取
            while ((n = fis.read(bytes)) != -1) {

                String s = new String(bytes, 0, n);
                System.out.print(s);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //最后一定要关闭文件流
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    //
//
//
//        InputStream in = null;
//        try {
//             in = getResources().getAssets().open("test");
//            byte[] bytes = new byte[16];
//            while (( in.read(bytes))!=-1){
//                String str = new String(bytes);
//                Log.d("yanghua",str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }finally {
////            try {
//            if(in != null) {
////                in.close();
//            }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }


}
