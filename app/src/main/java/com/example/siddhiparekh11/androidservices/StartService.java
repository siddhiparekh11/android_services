package com.example.siddhiparekh11.androidservices;


import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;

/**
 * Created by siddhiparekh11 on 4/4/17.
 */

public class StartService extends IntentService {

    private long[] enqueue;
    private DownloadManager dm;
    public StartService() {
        super("StartService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        int count=intent.getExtras().size();
        enqueue=new long[count];

       dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);

        for(int i=0;i<count;i++) {

            Request request = new Request(Uri.parse(intent.getStringExtra("Download" + Integer.toString(i + 1))));

            request.setTitle("PDF" + Integer.toString(i + 1) + "Download");


            request.setDescription("Android Data download using DownloadManager.");

            request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "PDF" + Integer.toString(i+1));
            enqueue[i]=dm.enqueue(request);
        }





    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {

        super.onStart(intent, startId);
    }
}

