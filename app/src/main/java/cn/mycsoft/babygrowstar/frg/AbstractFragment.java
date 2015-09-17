package cn.mycsoft.babygrowstar.frg;

import android.app.Fragment;

import cn.mycsoft.babygrowstar.StarApp;
import cn.mycsoft.babygrowstar.StarController;
import cn.mycsoft.babygrowstar.act.AbstractActivity;

/**
 * Fragment基类.
 * Created by MaYichao on 2015/9/17.
 */
public abstract class AbstractFragment extends Fragment {

    public AbstractActivity getStarAct() {
        return (AbstractActivity) getActivity();
    }

    public StarApp getApp() {
        return (StarApp) getStarAct().getApplication();
    }

    public StarController getController() {
        return getStarAct().getController();
    }


}
