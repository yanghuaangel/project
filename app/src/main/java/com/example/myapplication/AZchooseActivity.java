package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.myview.BladeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AZchooseActivity extends AppCompatActivity {

    private TextView mPrimaryText;
    private ImageView mThumbImage;
    private BladeView mLetterSelectView;

    private ListView mCountryList;
    private ArrayList<JsonBean.DataBean> datas = new ArrayList<>();
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_az_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.getClass().getSimpleName());

//        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mLetterSelectView = (BladeView) findViewById(R.id.country_list_letters);
        mLetterSelectView.setOnScollLister(mBladeViewLister);

        mThumbImage = findViewById(R.id.letter_selector_thumb);
        mPrimaryText = findViewById(R.id.letter_selector_txt);
//        mThumbImage.setX(-45);
//        Log.d("yanghua","mThumbImage X = " + mThumbImage.getX());

        mLetterSelectView.setLetterThumb(mThumbImage, mPrimaryText);

        mCountryList = findViewById(R.id.listview);

        getData();

        mCountryList.setAdapter(new MyAdapter(this,datas));


    }

    private void getData() {

        String jsonStr = "";

        InputStream in = null;

        in = this.getClass().getClassLoader().getResourceAsStream("assets/" + "data.json");
        InputStreamReader streamReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
            }
            reader.close();
            streamReader.close();
            in.close();
//            byte[] bytes = new byte[1024];
//            while ((in.read(bytes)) != -1) {
//                String str = new String(bytes);
//                jsonStr = jsonStr + str;
//            }
            jsonStr = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (streamReader != null) {
                    streamReader.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Logger.json(jsonStr);
        //GSON直接解析成对象
        JsonBean resultBean = new Gson().fromJson(jsonStr, JsonBean.class);
        datas.addAll(resultBean.getData());
        Log.d("yanghua ", "data siz =" + resultBean.getData().size());


    }

    private BladeView.OnScollLister mBladeViewLister = new BladeView.OnScollLister() {
        @Override
        public void onScoll(String itemValue) {
//            if (mCountryList == null || mCountryList.getAdapter() == null) {
//                return;
//            }
            for (int i = 0; i < mCountryList.getAdapter().getCount(); i++) {
                JsonBean.DataBean item = (JsonBean.DataBean) mCountryList.getAdapter().getItem(i);
                /*Log.i(TAG, "luotest i=" + i
                        +"    itemValue="+itemValue
                        + "    item=" + item);*/
                if (item != null && item.getText().toLowerCase().startsWith(itemValue.toLowerCase())) {
                    mCountryList.setSelection(item.getListPosition());
                    break;
                }
            }
            Log.d("yanghua", "onScroll _ name  =" + itemValue);
        }
    };

    private static class MyAdapter extends BaseAdapter {
        private ArrayList<JsonBean.DataBean> datas;
        private Context mContext;

        public MyAdapter(Context mContext, ArrayList<JsonBean.DataBean> data) {
            this.datas = data;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = new TextView(mContext);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText(datas.get(position).getText());
            return convertView;
        }

        public static class ViewHolder {
            public TextView textView;
        }
    }

}
