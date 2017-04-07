package com.example.siddhiparekh11.androidservices;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.ComponentName;
import  android.os.IBinder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by siddhiparekh11 on 4/4/17.
 */

public class PdfDownload extends Activity {

     Service1 bservice;
    boolean mbound=false;
    EditText ed1,ed2,ed3,ed4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_download);
        ed1=(EditText)findViewById(R.id.edit_pdf1);
        ed2=(EditText)findViewById(R.id.edit_pdf2);
        ed3=(EditText)findViewById(R.id.edit_pdf3);
        ed4=(EditText)findViewById(R.id.edit_pdf4);


    }
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            Service1.MyBinder binder = (Service1.MyBinder) service;
            bservice = binder.getService();
            mbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mbound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Where am I?","I am in onStart PdfDownload.java");




    }

    public void onStartDownload(View v)
    {
         Intent i=new Intent(this,StartService.class);
        i.putExtra("Download1",ed1.getText().toString());
        i.putExtra("Download2", ed2.getText().toString());
        i.putExtra("Download3", ed3.getText().toString());
        i.putExtra("Download4", ed4.getText().toString());
        startService(i);
    }


    public void onBindDownload(View v)
    {

        Intent i = new Intent(PdfDownload.this, Service1.class);
        try {
            URL[] urls = new URL[] {
                    new URL(ed1.getText().toString()),
                    new URL(ed2.getText().toString()),
                    new URL(ed3.getText().toString()),
                    new URL(ed4.getText().toString())};
            i.putExtra("URLs", urls);


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        bindService(i, mConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mbound) {
            unbindService(mConnection);
            mbound = false;
        }
    }
}
