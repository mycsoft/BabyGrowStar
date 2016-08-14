package cn.mycsoft.babygrowstar;

import android.content.Context;
import android.database.Cursor;

import java.util.Calendar;

import cn.mycsoft.babygrowstar.act.StarAppContext;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.entity.StarTask;

/**
 * 核心控制器.
 * 本类用于分离View与Model.
 * Created by MaYichao on 2015/8/30.
 */
public class StarController {
    Context context;
    DbHelper dbh;

    public StarController(Context context) {
        this.context = context;
        dbh = new DbHelper(context);

    }

    public StarController(StarAppContext context) {
        this.context = context.getContext();
        dbh = new DbHelper(this.context);

    }


    /**
     * 保存星星记录.
     *
     * @param star
     */
    public void insertStar(StarRecord star) {
        //保存星星
        dbh.insertStar(star);
    }

    public void updateStar(StarRecord star) {
        dbh.updateStar(star);
//        StatService.onEvent(context, "update star", "save", star.getNumber());
    }

    protected String getSql(int id, Object... ags) {
        return context.getResources().getString(id, ags);
    }

    public void onDestroy() {
        if (dbh != null)
            dbh.close();
    }

    public int queryStarTotal() {
//        dbh = new DbHelper(context);
        Cursor c = dbh.query(getSql(R.string.sql_star_total));
        c.moveToNext();
        int t = c.getInt(0);
        c.close();
//        dbh.close();
        return t;
    }

    /**
     * 查询所有添加记录,按时间倒序.
     *
     * @return
     */
    public Cursor findInputList() {
        Cursor c = dbh.query(getSql(R.string.sql_input_list));

        return c;
    }

    /**
     * 查询所有添加记录,按时间倒序.
     *
     * @return
     */
    public Cursor findTaskList() {
        Cursor c = dbh.query(getSql(R.string.sql_task_list));

        return c;
    }

    /**
     * 查询今天所有增加的星星总数.
     *
     * @return
     */
    public int queryStarTodayTotal() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long begin = calendar.getTimeInMillis();
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        long end = calendar.getTimeInMillis();
        Cursor c = dbh.query(getSql(R.string.sql_star_total_by_time, begin, end));
        c.moveToNext();
        int t = c.getInt(0);
        c.close();
//        dbh.close();
        return t;
    }

    /**
     * 根据id取得对象.
     *
     * @param id
     * @return
     */
    public StarRecord getStarById(Long id) {
        Cursor c = dbh.query(getSql(R.string.sql_select_star_by_id, id));
        if (c.getCount() < 1) {
            return null;
        }

        c.moveToNext();
        StarRecord star = StarRecord.parse(c);
        return star;
    }


    /**
     * 删除星星记录.
     *
     * @param id 记录id.
     */
    public void deleteStar(Long id) {
        dbh.deleteStar(id);
    }

    /**
     * 查询任务
     *
     * @param id
     * @return
     */
    public StarTask getTaskById(Long id) {
        Cursor c = dbh.query(getSql(R.string.sql_select_task_by_id, id));
        if (c.getCount() < 1) {
            return null;
        }

        c.moveToNext();
        StarTask star = StarTask.parse(c);
        return star;

    }

    public void insertTask(StarTask task) {
        //保存任务.
        dbh.insertTask(task);
    }

    public void updateTask(StarTask task) {
        dbh.updateTask(task);
    }

    public void deleteTask(Long id) {
        dbh.deleteTask(id);
    }

    /**
     * 查询出所有的宝贝信息。
     *
     * @return
     */
    public Cursor queryBabyList() {
        return dbh.queryAll("baby");
    }
}
