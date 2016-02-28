package com.avd.activity;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;
public class VibrateActivity extends AppCompatActivity implements ToggleButton.OnClickListener {
    private Vibrator mVibrator;
    private ToggleButton shortTimeVibrate;
    private ToggleButton longTimeVibrate;
    private ToggleButton rhythmVibrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrate);

        findViewById();
        Init();
        setListener();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.shortTimeVibrate:
                if(shortTimeVibrate.isChecked()){
                    startVibrate(new long[]{100, 10, 100, 1000}, -1);
                }
                else{
                    endVibrate();
                }

                break;
            case R.id.longTimeVibrate:
                if(longTimeVibrate.isChecked()){
                    startVibrate(new long[]{100, 100, 100, 1000}, 0);
                }
                else{
endVibrate();
                }
                break;
            case R.id.rhythmVibrate:
                if(rhythmVibrate.isChecked()){
                    startVibrate(new long[]{1000, 50, 1000, 50, 1000}, 0);
                }
                else {
                   endVibrate();
                }
                break;
        }
    }
    //开始震动 -1代表代表 0代表重复 若重复则循环多次震动pattern中的时间
    private void startVibrate(long[] pattern,int repeat){
        mVibrator.vibrate(pattern,repeat);
        showToast(getResources().getString(R.string.vibrate_start), Toast.LENGTH_SHORT);
    }

    //结束震动
    private void endVibrate(){
           mVibrator.cancel();
        showToast(getResources().getString(R.string.vibrate_end),Toast.LENGTH_SHORT);
    }


    //提示信息
    private void showToast(String showText,int duration) {
        Toast.makeText(getApplicationContext(), showText, duration).show();
    }
    /*界面的初始化工作*/
    private void Init() {
        mVibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
    }

    /*为控件设置事件监听*/
    private void setListener() {
          shortTimeVibrate.setOnClickListener(this);
        longTimeVibrate.setOnClickListener(this);
        rhythmVibrate.setOnClickListener(this);
    }

    /*实例化布局文件的控件*/
    private void findViewById() {
    shortTimeVibrate=(ToggleButton)findViewById(R.id.shortTimeVibrate);
        longTimeVibrate=(ToggleButton)findViewById(R.id.longTimeVibrate);
        rhythmVibrate=(ToggleButton)findViewById(R.id.rhythmVibrate);
    }
}
