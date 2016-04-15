package cn.mycsoft.babygrowstar.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.frg.InputListFragment;
import cn.mycsoft.babygrowstar.frg.PayItemFragment;

public class InputListAct extends AbstractLevel2Activity implements InputListFragment.OnFragmentInteractionListener, PayItemFragment.OnFragmentInteractionListener {

    public static final int RQ_CODE_ADD = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            AddActivity.startForAdd(this, RQ_CODE_ADD);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RQ_CODE_ADD:
                if (resultCode == AddActivity.R_CHANGED) {
                    //刷新列表.
                    InputListFragment fragment = (InputListFragment) getFragmentManager().findFragmentByTag("add");
                    fragment.reloadList();
                }
                break;
        }
    }

//    @Override
//    public void onFragmentInteraction(String id) {
//
//    }

//    @Override
//    public void onStarSelected(Long id) {
//        AddActivity.startForEdit(this, id, 2000);
//    }

    @Override
    public void onStarSelected(StarRecord star) {
        starApp.openEditAct(this, star);
    }
}
