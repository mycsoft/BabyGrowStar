package cn.mycsoft.babygrowstar;

import android.content.Context;
import android.database.Cursor;

import cn.mycsoft.babygrowstar.entity.StarRecord;

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


    /**
     * 保存星星记录.
     *
     * @param star
     */
    public void insertStart(StarRecord star) {
        //TODO 保存星星
//        dbh = new DbHelper(context);
//        dbh.update(getSql(R.string.sql_star_insert, '\'' + star.getNumber() + '\'', '\''+star.getDesc() + '\'', '\'' + star.getType().toString() + '\'', System.currentTimeMillis()));
//        SQLiteDatabase dbh = dbh.getWritableDatabase();
//        dbh.close();
//        dbh = null;
//        star.setTime(new Date());
        dbh.insertStar(star);
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
}
