package cn.mycsoft.babygrowstar;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.alibaba.sdk.android.feedback.util.IWxCallback;

/**
 * 应用的主后台服务。
 */
public class StarService extends Service {

    /**
     * 反馈通知。
     */
    public static String ACTION_FEEDBACK = "feedback";

    //    /**
//     * 监听器列表。
//     */
//    List<OnFeedBackChangedListener> listeners = new LinkedList<>();
    int unread = 0;

    private Handler objHandler = new Handler();

    private Runnable mTasks = new Runnable() {

        public void run()//运行该服务执行此函数
        {

            //检查反馈的未读数量。
            FeedbackAPI.getFeedbackUnreadCount(StarService.this, null, new IWxCallback() {
                @Override
                public void onSuccess(Object... objects) {
                    Integer i = (Integer) objects[0];
                    if (i != unread) {
                        unread = i;
                        //通知页面刷新。发广播
                        Intent intent = new Intent(ACTION_FEEDBACK).putExtra("unread", unread);
                        sendBroadcast(intent);
                    }
                }

                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onProgress(int i) {

                }
            });
            objHandler.postDelayed(mTasks, 10000);//每10秒执行一次
        }


    };//Runnable

    public StarService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启动时服务。
        objHandler.post(mTasks);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {


        StarBinder binder = new StarBinder();

        return binder;


    }


    public class StarBinder extends Binder {

    }

}
