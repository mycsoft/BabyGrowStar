package cn.mycsoft.babygrowstar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.umeng.analytics.MobclickAgent;

import cn.mycsoft.babygrowstar.act.AbstractActivity;
import cn.mycsoft.babygrowstar.act.AddActivity;
import cn.mycsoft.babygrowstar.act.AddRedeemActivity;
import cn.mycsoft.babygrowstar.act.AddStarFirstActivity;
import cn.mycsoft.babygrowstar.act.StarAppContext;
import cn.mycsoft.babygrowstar.entity.StarRecord;

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
        MobclickAgent.setDebugMode(true);
        FeedbackAPI.initAnnoy(this, "23355048");
    }

    public void addActivity(StarAppContext activity) {
        activity.setController(new StarController(activity));
    }


    public void openEditAct(AbstractActivity context, StarRecord star) {
        switch (star.getType()) {
            case add:
                AddActivity.startForEdit(context, star.getId() * 1l, 2000);
                break;
            case use:
                AddRedeemActivity.startForEdit(context, star.getId() * 1l, 2000);
                break;
        }
    }

    /**
     * 打开添加星星的画面.
     *
     * @param activity
     * @param resultCode
     */
    public void openFastAddStar(Activity activity, int resultCode) {
        //先打开快速选择画面.
        activity.startActivityForResult(new Intent(activity, AddStarFirstActivity.class), resultCode);
    }

    /**
     * 直接打开手动添加星星的画面.
     *
     * @param activity
     * @param resultCode
     */
    public void openManualAddStar(Activity activity, int resultCode) {
        AddActivity.startForAdd(activity, 1000);
    }

    /**
     * 根据任务生成添加星星.
     *
     * @param activity
     * @param requestCode
     * @param taskId
     */
    public void openAddStarWithTask(Activity activity, int requestCode, long taskId) {
        Intent i = new Intent(activity, AddActivity.class).putExtra("taskId", taskId);
        activity.startActivityForResult(i, requestCode);
    }

    public void opentFeedback(Context context) {
        //如果发生错误，请查看logcat日志
        FeedbackAPI.openFeedbackActivity(this);

    }
}
