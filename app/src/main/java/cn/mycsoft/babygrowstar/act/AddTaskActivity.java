package cn.mycsoft.babygrowstar.act;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarTask;

/**
 * 添加任务画面.
 */
public class AddTaskActivity extends AbstractLevel2Activity {

    public static final int R_CHANGED = 1000;
    public static final int R_UNCHANGED = 500;
    EditText nameEt;
    EditText numberEt;
    EditText descEt;
    //    TextView dateEt;
//    TextView timeEt;
//    DatePickerDialog dateDlg;
//    TimePickerDialog timeDlg;
    Mode mode = null;
    Long id = null;
    Calendar createTime = Calendar.getInstance();
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//    DateFormat dateFormat = SimpleDateFormat.getDateInstance();
//    DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

    /**
     * 删除按钮
     */
    Button deleteBtn;

    public static void startForAdd(Activity context, int requestCode) {

//        context.startActivity(new Intent(context, AddActivity.class));
        context.startActivityForResult(new Intent(context, AddTaskActivity.class), requestCode);
    }
//    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public static void startForEdit(Activity activity, Long id, int requestCode) {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra("id", id);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ActionBar actionBar = getActionBar();
        id = getIntent().getLongExtra("id", -1);
        if (id < 0) {
            id = null;
            mode = Mode.add;
        } else {
            mode = Mode.edit;
        }


        nameEt = (EditText) findViewById(R.id.name);
        numberEt = (EditText) findViewById(R.id.number);
        descEt = (EditText) findViewById(R.id.desc);
//        dateEt = (TextView) findViewById(R.id.date);
//        timeEt = (TextView) findViewById(R.id.time);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
//        if (actionBar != null)
//            actionBar.setDisplayShowHomeEnabled(true);

        //编辑时初始化信息.
        if (mode == Mode.edit) {
            initDateForEdit(id);
            //修改显示的标题栏.
            if (actionBar != null) {
                actionBar.setTitle(R.string.title_activity_edit_task);
//                actionBar.setIcon(R.drawable.ic_create_white_24dp);
            }
        } else {
            if (actionBar != null)
                //add
//                actionBar.setIcon(R.drawable.ic_add_box_white_24dp);
                //新增时不显示删除按钮.
                deleteBtn.setVisibility(View.GONE);

        }

//        initDateTimeEditor();
    }

    private void initDateForEdit(Long id) {
        StarTask star = getController().getTaskById(id);
        nameEt.setText(String.valueOf(star.getName()));
        numberEt.setText(String.valueOf(star.getNumber()));
        descEt.setText(star.getDesc());
        createTime.setTime(star.getCreateTime());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        initDateTimeEditor();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        paintDate();
//        paintTime();
    }

//    /**
//     * 初始化时间与日期控件.
//     */
//    private void initDateTimeEditor() {
//        //日期选择器.
//        dateDlg = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                createTime.set(year, monthOfYear, dayOfMonth);
//                paintDate();
//            }
//        }, createTime.get(Calendar.YEAR), createTime.get(Calendar.MONTH), createTime.get(Calendar.DAY_OF_MONTH));
//
//
//        //时间选择器.
//        timeDlg = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                createTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                createTime.set(Calendar.MINUTE, minute);
//                paintTime();
//            }
//        }, createTime.get(Calendar.HOUR_OF_DAY), createTime.get(Calendar.MINUTE), true);
//        timeEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timeDlg.show();
//            }
//        });
//    }
//
//    /**
//     * 绘制日期显示.
//     */
//    private void paintDate() {
//        dateEt.setText(dateFormat.format(createTime.getTime()));
//    }

//    /**
//     * 绘制日期显示.
//     */
//    private void paintTime() {
//        timeEt.setText(timeFormat.format(createTime.getTime()));
//    }

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
        StarTask task = new StarTask();

        //验证任务名称必填.
        String nameText = nameEt.getText().toString();
        if (nameText.isEmpty()) {
            Toast.makeText(this, "请输入任务名称", Toast.LENGTH_LONG).show();
            return;
        }

        //验证数量输入是否正确.
        String noText = numberEt.getText().toString();
        if (noText.isEmpty()) {
            Toast.makeText(this, "请输入星星的数量", Toast.LENGTH_LONG).show();
            return;
        }

        task.setName(nameText);
        task.setNumber(Integer.parseInt(noText));
        task.setType(StarTask.Type.simple);
        task.setCreateTime(createTime.getTime());
        task.setModifyTime(new Date());
        task.setDesc(descEt.getText().toString());
        try {
            switch (mode) {
                case add:
                    getController().insertTask(task);
                    break;
                case edit:
                    task.setId(id.intValue());
                    getController().updateTask(task);
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
                        getController().deleteTask(id);
                        setResult(R_CHANGED);
                        finish();
                    }
                }).setNegativeButton("暂时不删", null).setTitle("确定删除")
                .setMessage("确定要删除这个任务吗?删除任务不会影响已经生成的添加星星记录.")
                .create().show();

    }


    //运行模式.
    enum Mode {
        add, edit
    }
}
