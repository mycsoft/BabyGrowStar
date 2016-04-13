package cn.mycsoft.babygrowstar.act;

import android.content.Context;

import cn.mycsoft.babygrowstar.StarApp;
import cn.mycsoft.babygrowstar.StarController;

/**
 * 星星应用的专用上下文接口。
 * Created by MaYichao on 2016/4/13.
 */
public interface StarAppContext {


    /**
     * 返回控制器。
     *
     * @return
     */
    StarController getController();

    /**
     * 设置控制器
     *
     * @param controller
     */
    void setController(StarController controller);

    Context getContext();

    StarApp getStarApp();
}
