package cn.mycsoft.babygrowstar.act;

import android.app.Activity;
import android.os.Bundle;

import cn.mycsoft.babygrowstar.StarApp;
import cn.mycsoft.babygrowstar.StarController;

//import com.baidu.mobstat.StatService;

/**
 * Created by MaYichao on 2015/8/30.
 */
public abstract class AbstractActivity extends Activity {

    StarApp starApp;
    private StarController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 调试百度统计SDK的Log开关，可以在Eclipse中看到sdk打印的日志，发布时去除调用，或者设置为false
//        StatService.setDebugOn(true);

        starApp = (StarApp)getApplication();
        starApp.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        StatService.onPause(this);
    }

    @Override
    protected void onDestroy() {
        controller.onDestroy();
        super.onDestroy();
    }


    public StarController getController() {
        return controller;
    }

    public void setController(StarController controller) {
        this.controller = controller;
    }
}
