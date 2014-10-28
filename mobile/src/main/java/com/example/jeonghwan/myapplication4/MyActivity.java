package com.example.jeonghwan.myapplication4;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Log.d("tag1", "onCreate()");

        // 버튼 설정
        Button btnClickMe = (Button)findViewById(R.id.btnClickMe);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnClickMe = (Button)view.findViewById(R.id.btnClickMe);
                btnClickMe.setText(R.string.clicked);
                Log.d("tag1", "Clicked");
            }
        });

        // 토스트 보기 버튼 설정
        Button btnToast = (Button)findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "Toast clicked");
                Toast.makeText(v.getContext(), "토스트!", Toast.LENGTH_SHORT).show();
            }
        });

        // 다이얼로그 보기 버튼 설정
        Button btnDialog = (Button)findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "Dialog btn is clicked");

                final String[] cars = new String[] {"320i", "320d", "520d", "730i"};

                // new AlertDialog.Builder(v.getContext()).setIcon(R.drawable.ic_launcher).setTitle("인사")
                new AlertDialog.Builder(MyActivity.this).setIcon(R.drawable.ic_launcher).setTitle("인사")
                        // .setMessage("안녕하세요?")
                        .setItems(cars, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("tag", "cars: " + cars[i]);
                                Toast.makeText(MyActivity.this, "cars: " + cars[i], Toast.LENGTH_SHORT).show();
                                // 토스트를 띄우려면 컨텍스트를 어떻게 가져올수 있지? 2014/10/28
                            }
                        }).setNeutralButton("닫기", null).show();
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
