package manager.infrastructure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式工具类
 */
public class DateTimeUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取系统时间
     * @return
     */
    public static String getSysTime() {
        Date date = new Date();
        String dateStr = sdf.format(date);

        return dateStr;

    }

    /**
     * 将str转换为Date
     * @param date
     * @return
     */
    public Date str2Date(String date) {

        Date toDate = new Date();

        // TODO vip

        return toDate;
    }

}
