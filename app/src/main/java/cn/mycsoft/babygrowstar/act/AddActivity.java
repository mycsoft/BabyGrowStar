package cn.mycsoft.babygrowstar.act;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import cn.mycsoft.babygrowstar.entity.StarTask;

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
    Long id = null;
    Calendar createTime = Calendar.getInstance();
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    DateFormat dateFormat = SimpleDateFormat.getDateInstance();
    DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

    /**
     * 删除按钮
     */
    Button deleteBtn;

    public static void startForAdd(Activity context, int requestCode) {

//        context.startActivity(new Intent(context, AddActivity.class));
        context.startActivityForResult(new Intent(context, AddActivity.class), requestCode);
    }
//    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public static void startForEdit(AbstractActivity activity, Long id, int requestCode) {
        Intent intent = new Intent(activity, AddActivity.class);
        intent.putExtra("id", id);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getActionBar();
        id = getIntent().getLongExtra("id", -1);
        Long taskId = getIntent().getLongExtra("taskId", -1);
        if (id < 0) {
            id = null;
            mode = Mode.add;
        } else {
            mode = Mode.edit;
        }


        numberEt = (EditText) findViewById(R.id.number);
        descEt = (EditText) findViewById(R.id.desc);
        dateEt = (TextView) findViewById(R.id.date);
        timeEt = (TextView) findViewById(R.id.time);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
//        if (actionBar != null)
//            actionBar.setDisplayShowHomeEnabled(true);

        //编辑时初始化信息.
        if (mode == Mode.edit) {
            initDateForEdit(id);
            //修改显示的标题栏.
            if (actionBar != null) {
                actionBar.setTitle(R.string.title_activity_edit);
//                actionBar.setIcon(R.drawable.ic_create_white_24dp);
            }
        } else {
            if (actionBar != null)
                //add
//                actionBar.setIcon(R.drawable.ic_add_box_white_24dp);
            //新增时不显示删除按钮.
            deleteBtn.setVisibility(View.GONE);

            if (taskId != -1) {
                //按任务创建星星记录.

                //取出任务信息
                StarTask task = getController().getTaskById(taskId);
                if (task != null) {
                    //以任务信息为模板,修改默认值.
                    numberEt.setText(String.valueOf(task.getNumber()));
                    descEt.setText(task.getName());
                }
            }

        }

        initDateTimeEditor();
    }

    private void initDateForEdit(Long id) {
        StarRecord star = getController().getStarById(id);
        numberEt.setText(String.valueOf(star.getNumber()));
        descEt.setText(star.getDesc());
        createTime.setTime(star.getTime());
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

        //验证数量输入是否正确.
        String noText = numberEt.getText().toString();
        if (noText.isEmpty()) {
            Toast.makeText(this, "请输入星星的数量", Toast.LENGTH_LONG).show();
            return;
        }
        star.setNumber(Integer.parseInt(noText));
        star.setType(StarRecord.Type.add);
        star.setTime(createTime.getTime());
        star.setDesc(descEt.getText().toString());
        try {
            switch (mode) {
                case add:
                    getController().insertStar(star);
                    break;
                case edit:
                    star.setId(id.intValue());
                    getController().updateStar(star);
                    break;
            }
            setResult(R_CHANGED);
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "保存失败!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * 删除星星
     */
    public void delete(View view) {
        delete();
    }

    private void delete() {
        if (mode != Mode.edit) {
            //只有编辑模式下才有用.
            return;
        }
        new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setPositiveButton("狠心删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getController().deleteStar(id);
                        setResult(R_CHANGED);
                        finish();
                    }
                }).setNegativeButton("还是不删了吧", null).setTitle("删除星星")
                .setMessage("宝贝犯了什么错,一定要删除宝贝的星星吗?")
                .create().show();

    }


    //运行模式.
    enum Mode {
        add, edit
    }
}
