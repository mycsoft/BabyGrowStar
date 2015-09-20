package cn.mycsoft.babygrowstar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.mycsoft.babygrowstar.entity.StarRecord;

/**
 * Created by MaYichao on 2015/8/30.
 */
public class DbHelper extends SQLiteOpenHelper {

    Context context;

    public DbHelper(Context context) {
        super(context, "babyGrowStar", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.beginTransaction();
//        dbh.execSQL(StarRecord.Sql.CREATE);
        db.execSQL(context.getResources().getString(R.string.sql_star_create));
//        db.close();
//        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * 更新或插入.
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
        return db.rawQuery(sql,null);

    }

    /**
     * 添加星星
     * @param star
     * @return
     */
    public long insertStar(StarRecord star){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long count = db.insert("star_record", null, star.toContentValues());

//        db.close();
            db.setTransactionSuccessful();
            return count;
        }finally {
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
}
