package com.phil.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	 /** 
     * 获取前/后n天日期(M月d日) 
     * @return 
     */  
    public static String getMonthDay(int diff) {  
        DateFormat df = new SimpleDateFormat("MM月d日");  
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DAY_OF_YEAR, diff);  
        return df.format(c.getTime());
    }

}
