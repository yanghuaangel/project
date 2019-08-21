package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.myview.RemoteControllerView;

public class RemoteControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.getClass().getSimpleName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RemoteControllerView remoteControllerView = (RemoteControllerView) findViewById(R.id.rcv_view);
        remoteControllerView.setRemoteControllerClickListener(new RemoteControllerView.OnRemoteControllerClickListener() {
            @Override
            public void topClick() {
                Toast.makeText(RemoteControlActivity.this, "topClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void leftClick() {
                Toast.makeText(RemoteControlActivity.this, "leftClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(RemoteControlActivity.this, "rightClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void bottomClick() {
                Toast.makeText(RemoteControlActivity.this, "bottomClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void centerOkClick() {
                Toast.makeText(RemoteControlActivity.this, "centerOkClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
