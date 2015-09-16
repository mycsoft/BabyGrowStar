package cn.mycsoft.babygrowstar.entity;

import android.content.ContentValues;

import java.util.Date;

/**
 * 星星使用记录.
 * Created by MaYichao on 2015/8/30.
 */
public class StarRecord {

    private Integer id;
    /**
     * 调用时间
     */
    private Date time;

    private int number;

    /**
     * 记录类型
     */
    private Type type;
    /**
     * 说明
     */
    private String desc;

    /**
     * 调用时间
     */
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        if (id != null){
            cv.put("_id",id);
        }

        cv.put("number",number);
        if (time != null){
            cv.put("time",time.getTime());
        }
        if (desc != null){
            cv.put("`desc`",desc);
        }
        if (type != null){
            cv.put("type",type.toString());
        }
        return cv;
    }

    /**
     * 记录类型
     */
    public static enum Type {
        /**
         * 添加记录
         */
        add,
        /**
         * 使用记录(减少)
         */
        use
    }

    public static class Sql {
        public static final String  CREATE = "create table star_record { " +
                "_id Integer PRIMARY KEY AUTOINCREMENT," +
                "number Integer not null," +
                "time Long not null default now()," +
                "type Varchar," +
                "`desc` varchar" +
                "}";
    }
}