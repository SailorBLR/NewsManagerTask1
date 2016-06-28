package by.epam.hubarevich.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Anton_Hubarevich on 6/21/2016.
 */
public class TimestamperUtil {
    public static Timestamp makeTimeStamp (){
        Date today = new Date();
        return new Timestamp(today.getTime());
    }
}
