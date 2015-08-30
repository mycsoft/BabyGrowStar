package cn.mycsoft.babygrowstar;

import android.app.Activity;
import android.app.Application;

import cn.mycsoft.babygrowstar.act.AbstractActivity;

/**
 * 星星应用主类.
 * 本系统采用mvc模式.M与V之间采用监听器模式对接.
 * Created by MaYichao on 2015/8/30.
 */
public class StarApp extends Application {

    StarController controller;

    public void addActivity(AbstractActivity activity){
        activity.setController(new StarController(activity));
    }
}
