package com.example.siddhiparekh11.androidservices;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.os.Binder;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by siddhiparekh11 on 4/4/17.
 */

public class Service1 extends Service {
    private long enqueue;
    private DownloadManager dm;
    public URL[] urls;
    private final IBinder binder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.d("Where am I?", "I am in onBind method");

        Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
        URL[] urls = new URL[objUrls.length];
        for (int i=0; i<objUrls.length; i++) {
            urls[i] = (URL) objUrls[i];
        }
        new DoBackgroundTask().execute(urls);

        return binder;
    }

    public class MyBinder extends Binder {
        Service1 getService() {
            return Service1.this;
        }
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private long DownloadFile(URL url, int cou) {

        Log.d("URL",url.toString());

        dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);


        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url.toString()));

        request.setTitle("PDF" + Integer.toString(cou+1) + "Download");


        request.setDescription("Android Data download using DownloadManager.");

        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS,"PDF" + Integer.toString(cou));


        enqueue=dm.enqueue(request);
        return enqueue;

    }


    // this is the method for the client
    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {

        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            Long s=0L;
            Log.d("The lenght of the URL",String.valueOf(count));
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {

                s=DownloadFile(urls[i],i);
                Log.d("enqueue value",Long.toString(s));

            }
               return s;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Long result) {

            stopSelf();
        }
    }
}
