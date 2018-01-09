package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by tingkuanlin on 2018/1/6.
 */

public class CountDownActivity extends Activity {


    private EditText dst_min_edit_text = null;
    private TextView remain_text_view = null;
    private Button start_btn = null;
    private Button halt_btn = null;

    private CountDownTimer countDownTimer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        dst_min_edit_text = (EditText) findViewById(R.id.dst_min_editText);
        remain_text_view = (TextView) findViewById(R.id.remain_textView);
        start_btn = (Button) findViewById(R.id.start_button);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCountDown();
            }
        });

        halt_btn = (Button) findViewById(R.id.halt_button);
        halt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haltForCountDown();
            }
        });
        setOriginalState();

    }
    private void startCountDown(){

        int countdown = (Integer.parseInt(dst_min_edit_text.getText().toString()))*60*1000;
        int update_frequency = 1000;

        countDownTimer = new CountDownTimer(countdown,update_frequency){

            @Override
            public void onFinish() {
                setOriginalState();
                remain_text_view.setText("時間到!");
                vibrateTimeOut();

                new AlertDialog.Builder(CountDownActivity.this)
                        .setMessage("時間到")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 return;
                            }
                        })
                        .show();

            }

            @Override
            public void onTick(long millisUntilFinished) {
                setCountDownState();
                remain_text_view.setText("剩下"+millisUntilFinished/1000+"秒");
            }

        }.start();

    }
    private void haltForCountDown(){

        if(countDownTimer != null)
            countDownTimer.cancel();

        Intent intent = new Intent();
        intent.setClass(CountDownActivity.this, MainActivity.class);
        startActivity(intent);

    }
    private void setCountDownState(){

        dst_min_edit_text.setEnabled(false);
        start_btn.setEnabled(false);
        halt_btn.setEnabled(true);

    }
    private void setOriginalState(){

        dst_min_edit_text.setEnabled(true);
        start_btn.setEnabled(true);
        halt_btn.setEnabled(false);

    }
    private void vibrateTimeOut(){

        Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        myVibrator.vibrate(new long[]{1000, 2000, 1000, 2000, 1000, 2000}, -1);

    }
    private void soundTimeOut(){

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        notification.defaults = Notification.DEFAULT_SOUND;
        notificationManager.notify(3000, notification);

    }



}
