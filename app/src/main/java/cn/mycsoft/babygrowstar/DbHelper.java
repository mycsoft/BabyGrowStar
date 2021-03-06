package cn.mycsoft.babygrowstar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import cn.mycsoft.babygrowstar.entity.Baby;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.entity.StarTask;

/**
 * Created by MaYichao on 2015/8/30.
 */
public class DbHelper extends SQLiteOpenHelper {

    Context context;

    public DbHelper(Context context) {
        super(context, "babyGrowStar", null, 6);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.beginTransaction();
//        dbh.execSQL(StarRecord.Sql.CREATE);
        createFor1(db);
        createFor2(db);
        createFor3(db);
        createFor6(db);
//        db.close();
//        db.endTransaction();
    }

    private void createFor1(SQLiteDatabase db) {
        db.execSQL(context.getResources().getString(R.string.sql_star_create));
    }

    private void createFor2(SQLiteDatabase db) {
        //建表
        db.execSQL(context.getResources().getString(R.string.sql_task_create));

    }

    /**
     * 修正第3版漏建任务表的错误。
     */
    private void doFix3Bug(SQLiteDatabase db) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
        //检查任务表是否存在。
        try {
            db.rawQuery("select  count(0) from star_task", null);
        } catch (Exception e) {
            createFor2(db);
            createFor3(db);
        }

//        db.close();
//        db.endTransaction();
    }

    private void createFor3(SQLiteDatabase db) {

        //加入默认任务.
        String[][] defTasks = {{"按时吃饭", "1"}, {"洗碗", "2"}, {"叠被子", "1"}};
        for (String[] taskString : defTasks) {
            StarTask task = new StarTask();
            task.setName(taskString[0]);
            task.setNumber(Integer.parseInt(taskString[1]));
            task.setCreateTime(new Date());
            task.setModifyTime(task.getCreateTime());
            task.setType(StarTask.Type.simple);
//            insertTask(task);
            db.insert("star_task", null, task.toContentValues());
        }
    }

    /**
     * 升级到6版数据库，加入多宝宝的设置。
     *
     * @param db
     */
    private void createFor6(SQLiteDatabase db) {
        //升级到多宝宝版
        db.execSQL(context.getResources().getString(R.string.sql_baby_create));
        //增加第一个默认的宝宝
        Baby baby = new Baby();
        baby.setName("宝宝");
        baby.setCreateTime(new Date());
        baby.setModifyTime(new Date());
//        insertBaby(baby);
        db.insert("baby", null, baby.toContentValues());
        //星星记录中增加宝宝的关联
        db.execSQL(context.getResources().getString(R.string.sql_star_upgrad_m_baby));

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                createFor2(db);
            case 2:
                createFor3(db);
            case 3:
                if (oldVersion == 3) {
                    doFix3Bug(db);
                }
            case 4:
            case 5:
                //升级到多宝宝版
                createFor6(db);


        }
    }


    /**
     * 更新或插入.
     *
     * @param sql
     * @return
     */
    public void update(String sql) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.execSQL(sql);

//        db.close();
        db.endTransaction();
    }

    public Cursor query(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);

    }

    /**
     * 查询一个列表中的所有信息。
     *
     * @param table
     * @return
     */
    public Cursor queryAll(String table) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from " + table, null);
    }

    /**
     * 添加星星
     *
     * @param star
     * @return
     */
    public long insertStar(StarRecord star) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.insert("star_record", null, star.toContentValues());

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    public long updateStar(StarRecord star) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.update("star_record", star.toContentValues(), "_id = ?",
                    new String[]{String.valueOf(star.getId())});

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    /**
     * 删除星星记录.
     *
     * @param id 记录id.
     */
    public long deleteStar(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.delete("star_record", "_id = ?",
                    new String[]{String.valueOf(id)});

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    public long insertTask(StarTask task) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.insert("star_task", null, task.toContentValues());

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    public long updateTask(StarTask task) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.update("star_task", task.toContentValues(), "_id = ?",
                    new String[]{String.valueOf(task.getId())});

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    public long deleteTask(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.delete("star_task", "_id = ?",
                    new String[]{String.valueOf(id)});

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

    /**
     * 插入宝宝信息。
     *
     * @param baby
     * @return
     */
    private long insertBaby(Baby baby) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.insert("baby", null, baby.toContentValues());

//        db.close();
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();

        }
    }

//    /**
//     * 数据库操作类.
//     */
//    private abstract class DHOperater {
//
//
//    }
}
