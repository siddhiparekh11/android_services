package com.example.siddhiparekh11.androidservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onDownload(View v)
    {
        Intent i=new Intent(this,PdfDownload.class);
        startActivity(i);

    }

    public void onClose(View v)
    {
        this.finish();
    }
}
