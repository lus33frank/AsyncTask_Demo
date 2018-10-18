package com.frankchang.asynctask_demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShow = findViewById(R.id.tvShow);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new ConnectTask().execute("http://tw.yahoo.com");
    }


    public class ConnectTask extends AsyncTask<String, Void, Integer> {

        // 動作執行前
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 動作執行後
        @Override
        protected void onPostExecute(Integer data) {
            super.onPostExecute(data);

            tvShow.setText(String.valueOf(data));
        }

        @Override
        protected Integer doInBackground(String... strings) {
            int data = 0;

            try {
                URL url = new URL(strings[0]);
                data = url.openStream().read();
                Log.d(TAG, "doInBackground: " + data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }
    }
}
