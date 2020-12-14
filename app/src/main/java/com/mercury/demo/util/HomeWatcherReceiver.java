package com.mercury.demo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mercury.demo.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String TAG = "HomeWatcherReceiver";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";


    private static List<HomePressListener> sHomePressListenerList = new ArrayList<>();
    private static Long sLastClickRecent;


    public static void addHomePressListeners(@NonNull HomePressListener l){
        if(!sHomePressListenerList.contains(l)){
            sHomePressListenerList.add(l);
        }

    }


    public static void removeHomePressListener(@NonNull HomePressListener l){
        sHomePressListenerList.remove(l);
    }


    public HomeWatcherReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if(SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)){
                if(sHomePressListenerList != null && sHomePressListenerList.size()>0){
                    for(HomePressListener l:sHomePressListenerList){
                        if(l != null){
                            if(l instanceof AvoidRecentPressListener){
                                if(System.currentTimeMillis() -sLastClickRecent >1000L*10){
                                    l.onHomePressed();
                                }else{
                                    Log.e("LOG_TAG", "距离上次点击任务键盘点击不超过 10s，忽略本次 home 键消息");
                                }
                            }
                        }
                    }
                }
                if (System.currentTimeMillis() - sLastClickRecent > 1000L * 10) {
                } else {
                    Log.e("ad_sdk_home", "距离上次点击任务键盘点击不超过 10s，忽略本次 home 键消息");
                }
            }else if(SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)){
                sLastClickRecent = System.currentTimeMillis();
            }else if(SYSTEM_DIALOG_REASON_LOCK.equals(reason)){
                //锁屏
            }else if(SYSTEM_DIALOG_REASON_ASSIST.equals(reason)){
                //samsung 长按home 键
            }


        }

    }


    /**
     * 点击切换任务键，按返回或者切换应用系统都会当成触发一次 home 键消息
     * 有些情况下需要一个规避逻辑，例如按 Recent 键后 10s 内的 home 消息自动屏蔽
     */
    public abstract static class AvoidRecentPressListener implements HomePressListener {

        public AvoidRecentPressListener() {
        }
    }
}
