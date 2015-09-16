package cn.mycsoft.babygrowstar.act;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;

public class AddActivity extends AbstractLevel2Activity {

    public static final int R_CHANGED = 1000;
    public static final int R_UNCHANGED = 500;
    EditText numberEt;
    EditText descEt;
    TextView dateEt;
    TextView timeEt;
    DatePickerDialog dateDlg;
    TimePickerDialog timeDlg;
    Mode mode = null;
    Integer id = null;
    Calendar createTime = Calendar.getInstance();
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    DateFormat dateFormat = SimpleDateFormat.getDateInstance();
    DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

    public static void startForAdd(AbstractActivity context, int requestCode) {

//        context.startActivity(new Intent(context, AddActivity.class));
        context.startActivityForResult(new Intent(context, AddActivity.class), requestCode);
    }
//    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy年MM月dd日");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        id = getIntent().getIntExtra("id", -1);
        if (id < 0) {
            id = null;
            mode = Mode.add;
        } else {
            mode = Mode.edit;
        }

        //TODO 编辑时初始化信息.


        numberEt = (EditText) findViewById(R.id.number);
        descEt = (EditText) findViewById(R.id.desc);
        dateEt = (TextView) findViewById(R.id.date);
        timeEt = (TextView) findViewById(R.id.time);

        initDateTimeEditor();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initDateTimeEditor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paintDate();
        paintTime();
    }

    /**
     * 初始化时间与日期控件.
     */
    private void initDateTimeEditor() {
        //日期选择器.
        dateDlg = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                createTime.set(year, monthOfYear, dayOfMonth);
                paintDate();
            }
        }, createTime.get(Calendar.YEAR), createTime.get(Calendar.MONTH), createTime.get(Calendar.DAY_OF_MONTH));
        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDlg.show();
            }
        });

        //时间选择器.
        timeDlg = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                createTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                createTime.set(Calendar.MINUTE, minute);
                paintTime();
            }
        }, createTime.get(Calendar.HOUR_OF_DAY), createTime.get(Calendar.MINUTE), true);
        timeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDlg.show();
            }
        });
    }

    /**
     * 绘制日期显示.
     */
    private void paintDate() {
        dateEt.setText(dateFormat.format(createTime.getTime()));
    }

    /**
     * 绘制日期显示.
     */
    private void paintTime() {
        timeEt.setText(timeFormat.format(createTime.getTime()));
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
        switch (id) {
            case R.id.action_save: //对用户按设置的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                save();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 保存星星
     */
    public void save(View view) {
        save();
    }

    private void save() {
        StarRecord star = new StarRecord();
        star.setNumber(Integer.parseInt(numberEt.getText().toString()));
        star.setType(StarRecord.Type.add);
        star.setTime(createTime.getTime());
        star.setDesc(descEt.getText().toString());
        try {
            getController().insertStart(star);
            setResult(R_CHANGED);
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "保存失败!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    //运行模式.
    enum Mode {
        add, edit
    }
}
