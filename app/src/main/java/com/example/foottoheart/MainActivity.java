package com.example.foottoheart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static int counter = 0;
    static int timer = 0;
    boolean isstop = true;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 운동 횟수 카운트---------------------
        // 임시로 만들었기 때문에 버튼을 눌렀을 때 운동횟수 증가
        // 실제로는 센서에서 운동이 감지되었을 때 횟수 증가
        tv1 = (TextView)findViewById(R.id.Daily_count);
        Button count_BT = (Button) findViewById(R.id.Bt) ;
        count_BT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tv1.setText(Integer.toString(counter));

            }
        });

        // 앉아있는 시간 카운트--------------------
        // 임시로 만들었기때문에 start버튼을 누를 시 시간 증가, stop 버튼을 누를 시 시간 정지
        // 실제로는 센서에서 앉았다고 판단되었을 시 시간 증가
        // 리얼 수정이 안되나?
        tv2 = (TextView)findViewById(R.id.daily_time);
        Button timer_startBT = (Button) findViewById(R.id.start) ;
        timer_startBT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isstop) {
                    isstop = false;
                    mHandler.sendEmptyMessage(0);
                }
            }
        });

        Button timer_stopBT = (Button) findViewById(R.id.stop) ;
        timer_stopBT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                isstop = true;
                mHandler.removeMessages(0);
            }
        });

    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            timer++;

            tv2.setText(String.format("%02d시간 %02d분 %02d초", timer / (60 * 60) , (timer/60)%60, (timer % 60)));

            // 메세지를 처리하고 또다시 핸들러에 메세지 전달 (1000ms 지연)
            mHandler.sendEmptyMessageDelayed(0,1000);
        }
    };




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
        if (id == R.id.nujeuk) {
            return true;
        }
        if (id == R.id.friend) {
            return true;
        }
        if (id == R.id.news) {
            return true;
        }
        if (id == R.id.setting) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
