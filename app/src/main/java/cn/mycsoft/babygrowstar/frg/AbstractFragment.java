package cn.mycsoft.babygrowstar.frg;

import android.app.Fragment;

import cn.mycsoft.babygrowstar.StarApp;
import cn.mycsoft.babygrowstar.StarController;
import cn.mycsoft.babygrowstar.act.AbstractActivity;
import cn.mycsoft.babygrowstar.act.StarAppContext;

/**
 * Fragment基类.
 * Created by MaYichao on 2015/9/17.
 */
public abstract class AbstractFragment extends Fragment {

    @Deprecated
    public AbstractActivity getStarAct() {
        return (AbstractActivity) getActivity();
    }

    public StarAppContext getStarAppContext() {
        return (StarAppContext) getActivity();
    }

    public StarApp getApp() {
        return getStarAppContext().getStarApp();
    }

    public StarController getController() {
        return getStarAppContext().getController();
    }


}
