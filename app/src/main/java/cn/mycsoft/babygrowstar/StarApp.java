package cn.mycsoft.babygrowstar;

import android.app.Application;

import cn.mycsoft.babygrowstar.act.AbstractActivity;

/**
 * 星星应用主类.
 * 本系统采用mvc模式.M与V之间采用监听器模式对接.
 * Created by MaYichao on 2015/8/30.
 */
public class StarApp extends Application {

    StarController controller;

    @Override
    public void onCreate() {
        super.onCreate();
//        UmengUpdateAgent.setUpdateUIStyle(UpdateStatus.STYLE_NOTIFICATION);
//        UmengUpdateAgent.setUpdateAutoPopup(false);
//        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
//            @Override
//            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
//                Context mContext = StarApp.this;
//                switch (updateStatus) {
//                    case UpdateStatus.Yes: // has update
//                        UmengUpdateAgent.showUpdateDialog(mContext, updateInfo);
//                        break;
//                    case UpdateStatus.No: // has no update
//                        Toast.makeText(mContext, "没有更新", Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.NoneWifi: // none wifi
//                        Toast.makeText(mContext, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.Timeout: // time out
//                        Toast.makeText(mContext, "超时", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });
//        UmengUpdateAgent.update(this);
//        UmengUpdateAgent.silentUpdate(this);
    }

    public void addActivity(AbstractActivity activity){
        activity.setController(new StarController(activity));
    }
}
