package com.example.jeonghwan.myapplication4;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

import java.sql.SQLException;


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

        // 추가 버튼 핸들러
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText carNameCtrl = (EditText) findViewById(R.id.textinputAdd);
                String carName = carNameCtrl.getText().toString();
                Log.d("tag", carName);

                DBHandler dbHandler = null;
                try {
                    dbHandler = DBHandler.open(MyActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                long cnt = dbHandler.insert(carName);

                Log.d("tag", "" + cnt);
                if (cnt == -1) {
                    Toast.makeText(MyActivity.this,
                            carName + "가 테이블 추가 실패",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyActivity.this,
                            carName + "가 테이블 추가 성공",
                            Toast.LENGTH_SHORT).show();
                }

                dbHandler.close();
            }
        });

        // 조회 버튼 핸들러
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchCtrl = (EditText) findViewById(R.id.textinputSearch);
                int searchNum = Integer.parseInt(searchCtrl.getText().toString());

                DBHandler dbHandler = null;
                try {
                    dbHandler = DBHandler.open(MyActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Cursor cursor = dbHandler.select(searchNum);
                startManagingCursor(cursor);

                if (cursor.getCount() == 0) {
                    Toast.makeText(MyActivity.this,
                            "데이터가 없음",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String val = cursor.getString(cursor.getColumnIndex("car_name"));
                    Toast.makeText(MyActivity.this,
                            val,
                            Toast.LENGTH_SHORT).show();
                }

                dbHandler.close();
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
