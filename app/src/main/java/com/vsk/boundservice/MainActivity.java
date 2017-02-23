package com.vsk.boundservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.vsk.boundservice.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService buckysService;
    boolean isBound = false;

    //Method responsible for showing the time
    public void showTime(View view){
        String currentTime = buckysService.getCurrentTime();
        TextView buckysText = (TextView) findViewById(R.id.buckysText);
        buckysText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, MyService.class);
        bindService(i, buckysConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection buckysConnection = new ServiceConnection() {
        @Override

        //action to be taken when the service gets connected
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalBinder binder = (MyLocalBinder) iBinder;
            buckysService = binder.getService();
            isBound = true;
        }

        @Override
        //action to be taken when the service gets disconnected
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

}
