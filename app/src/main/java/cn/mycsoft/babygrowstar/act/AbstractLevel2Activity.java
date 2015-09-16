package cn.mycsoft.babygrowstar.act;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import cn.mycsoft.babygrowstar.R;

/**
 * 二级页上画面的Activity基类.
 * Created by MaYichao on 2015/9/16.
 */
public abstract class AbstractLevel2Activity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home: //对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                return onHomeBtnClick(item);
            case R.id.action_settings: //对用户按设置的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 返回按钮按下事件.
     *
     * @param item
     * @return
     */
    protected boolean onHomeBtnClick(MenuItem item) {
        finish();
        return true;
    }
}
