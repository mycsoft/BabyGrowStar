package cn.mycsoft.babygrowstar.entity;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

/**
 * 宝宝对象类。
 * Created by MaYichao on 2016/8/14.
 */
public class Baby {

    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;

    private Date modifyTime;

    private Sexy sexy;


    /**
     * 名字
     */
    private String name;

    private String photo;
    private Date birthday;

    /**
     * 从数据库表行,转换为数据对象.
     *
     * @param cursor
     * @return
     */
    public static Baby parse(Cursor cursor) {
        Baby baby = new Baby();
        baby.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        baby.setName(cursor.getString(cursor.getColumnIndex("name")));
        baby.setPhoto(cursor.getString(cursor.getColumnIndex("photo")));
        baby.setCreateTime(new Date(cursor.getLong(cursor.getColumnIndex("createTime"))));
        baby.setModifyTime(new Date(cursor.getLong(cursor.getColumnIndex("modifyTime"))));
        baby.setBirthday(new Date(cursor.getLong(cursor.getColumnIndex("birthday"))));
        baby.setSexy(Sexy.valueOf(cursor.getString(cursor.getColumnIndex("sexy"))));
        return baby;
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


        if (birthday != null) {
            cv.put("birthday", birthday.getTime());
        }
        if (createTime != null) {
            cv.put("createTime", createTime.getTime());
        }
        if (modifyTime != null) {
            cv.put("modifyTime", modifyTime.getTime());
        }
        if (photo != null) {
            cv.put("`photo`", photo);
        }


        cv.put("`name`", name);

        if (sexy != null) {
            cv.put("sexy", sexy.toString());
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
     * 性别
     */
    public Sexy getSexy() {
        return sexy;
    }

    public void setSexy(Sexy sexy) {
        this.sexy = sexy;
    }

    /**
     * 照片（位置）
     */
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    /**
     * 性别
     */
    public enum Sexy {
        /**
         * 男孩。
         */
        boy,
        /**
         * 女孩
         */
        girl
    }

}
