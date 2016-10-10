package com.phil.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	//完整的时间格式，年月日时分秒
	public static final SimpleDateFormat Y_M_D_H_M_S_DATE= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//标准的时间格式，年月日时分
	public static final SimpleDateFormat Y_M_D_DATE= new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat YMDHM_DATE = new SimpleDateFormat("yyyy年M月d日HH时mm分");
	//只有年月日的时间格式
	public static final SimpleDateFormat YMD_DATE = new SimpleDateFormat("yyyy年M月d日");
	
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
    
    /**
	 * @param sdf    日期字符串的格式
	 * @param sf     要转换的日期之后
	 * @param sf     日期字符串
	 * @return
	 */
	public static String dateFormat(SimpleDateFormat sdf,SimpleDateFormat df,String str){		
		String time ="";
		try {
			Date date = sdf.parse(str);
			time = df.format(date);		
		} catch (Exception e) {
			e.printStackTrace();
			time = "无法获取到准确时间";
		}
		return time;	
	}

}
