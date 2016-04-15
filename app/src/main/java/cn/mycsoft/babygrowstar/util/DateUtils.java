package cn.mycsoft.babygrowstar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类.
 * Created by MaYichao on 2016/4/15.
 */
public class DateUtils {

    /**
     * 根据当前时间格式化时间的显示.
     *
     * @param date
     * @return <pre>
     *     今天的,会显示刚刚,5分钟前,10分钟,或时间;
     *     今天以前的,会显示为昨天,前天,具体日期.
     * </pre>
     */
    public static String formatTimeFromNow(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        String timeString = null;
        DateFormat format = null;
        if (now.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
            //同一年.

            //与今天的差距(天).
            int dT = now.get(Calendar.DAY_OF_YEAR) - time.get(Calendar.DAY_OF_YEAR);
            switch (dT) {
                case 0: //今天显示时间
                    long st = (now.getTimeInMillis() - time.getTimeInMillis()) / 60000;
                    if (st <= 1) {
                        timeString = "刚刚";
                    } else if (st < 30) {
                        timeString = st + "分钟前";
                    } else if (st < 60) {
                        timeString = "半小时前";
                    } else if (st < 90) {
                        timeString = "1小时前";
                    } else {
                        format = new SimpleDateFormat("HH:mm");
                        timeString = format.format(time.getTime());
                    }

                    break;
                case 1: //昨天
                    timeString = "昨天";
                    break;
                case 2: //前天
                    timeString = "前天";
                    break;
                default:    //同年,只显示月日.
                    format = new SimpleDateFormat("MM月dd日");
                    timeString = format.format(time.getTime());
                    break;
            }

        } else {
            //不在同一年,只显示到日期.
            format = new SimpleDateFormat("yyyy年MM月dd日");
            timeString = format.format(time.getTime());
        }

        return timeString;
    }
}
