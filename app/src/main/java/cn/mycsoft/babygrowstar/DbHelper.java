package cn.mycsoft.babygrowstar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.beginTransaction();
//        dbh.execSQL(StarRecord.Sql.CREATE);
        db.execSQL(context.getResources().getString(R.string.sql_star_create));
//        db.close();
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
    }

    public Cursor query(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);

    }
}
