package cn.mycsoft.babygrowstar.entity;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

/**
 * 星星任务.
 * Created by MaYichao on 2016/4/13.
 */
public class StarTask {

    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;

    private Date modifyTime;

    private int number;

    /**
     * 任务类型
     */
    private Type type;

    private String name;
    /**
     * 说明
     */
    private String desc;

    /**
     * 从数据库表行,转换为数据对象.
     *
     * @param cursor
     * @return
     */
    public static StarTask parse(Cursor cursor) {
        StarTask star = new StarTask();
        star.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        star.setName(cursor.getString(cursor.getColumnIndex("name")));
        star.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
        star.setCreateTime(new Date(cursor.getLong(cursor.getColumnIndex("createTime"))));
        star.setModifyTime(new Date(cursor.getLong(cursor.getColumnIndex("modifyTime"))));
        star.setType(Type.valueOf(cursor.getString(cursor.getColumnIndex("type"))));
        star.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
        return star;
    }

    /**
     * 调用时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 记录类型
     */
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 说明
     */
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != null) {
            cv.put("_id", id);
        }

        cv.put("number", number);
        if (createTime != null) {
            cv.put("createTime", createTime.getTime());
        }
        if (modifyTime != null) {
            cv.put("modifyTime", modifyTime.getTime());
        }
        if (desc != null) {
            cv.put("`desc`", desc);
        }


        cv.put("`name`", name);

        if (type != null) {
            cv.put("type", type.toString());
        }
        return cv;
    }

    /**
     * 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 任务名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * 记录类型
     */
    public enum Type {
        /**
         * 简单任务。
         */
        simple,
        /**
         * 每日任务
         */
        everyday
    }

//    public static class Sql {
//        public static final String  CREATE = "create table star_record { " +
//                "_id Integer PRIMARY KEY AUTOINCREMENT," +
//                "number Integer not null," +
//                "createTime Long not null default now()," +
//                "type Varchar," +
//                "`desc` varchar" +
//                "}";
//    }
}
