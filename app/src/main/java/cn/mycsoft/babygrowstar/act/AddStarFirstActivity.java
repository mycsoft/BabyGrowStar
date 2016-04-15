package cn.mycsoft.babygrowstar.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.mycsoft.babygrowstar.R;

/**
 * 添加星星的前置画面.用户可以在本画面上选择,按任务添加或者手动添加.
 */
public class AddStarFirstActivity extends AbstractLevel2Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_star_first);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_star_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_manual_add) {
            getStarApp().openManualAddStar(this, 1000);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case AddActivity.R_CHANGED:
                        finish();
                        break;
                }

                break;

        }
    }
}
