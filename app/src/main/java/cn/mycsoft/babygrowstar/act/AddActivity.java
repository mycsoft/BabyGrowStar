package cn.mycsoft.babygrowstar.act;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;

public class AddActivity extends AbstractActivity {

    EditText numberEt;
    EditText descEt;
    EditText dateEt;
    EditText timeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        numberEt = (EditText)findViewById(R.id.number);
        descEt = (EditText)findViewById(R.id.desc);
        dateEt = (EditText)findViewById(R.id.date);
        timeEt = (EditText)findViewById(R.id.time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 保存星星
     */
    public void save(View view) {
        StarRecord star = new StarRecord();
        star.setNumber(Integer.parseInt(numberEt.getText().toString()));
        star.setType(StarRecord.Type.add);
        getController().insertStart(star);
        finish();
    }
}
