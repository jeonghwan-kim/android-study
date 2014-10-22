package com.example.jeonghwan.myapplication4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Log.i("tag1", "onCreate()");
        TextView tvClickMe = (TextView)findViewById(R.id.textViewClickMe);
        tvClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            TextView tvClickMe = (TextView)view.findViewById(R.id.textViewClickMe);
            tvClickMe.setText(R.string.clicked);
            Log.i("tag", "Clicked");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
