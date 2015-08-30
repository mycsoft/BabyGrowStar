package cn.mycsoft.babygrowstar.act;

import android.app.Activity;
import android.os.Bundle;

import cn.mycsoft.babygrowstar.StarApp;
import cn.mycsoft.babygrowstar.StarController;

/**
 * Created by MaYichao on 2015/8/30.
 */
public abstract class AbstractActivity extends Activity {

    StarApp starApp;
    private StarController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starApp = (StarApp)getApplication();
        starApp.addActivity(this);
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
