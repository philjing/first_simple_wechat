package com.phil.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 字符串辅助类
 * @author fjing
 *
 */
public class StringHelper {
	
	public static String escape(String src) {
		if(src==null)return "";
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	public static String randomCode(String prefix,int len){
		return prefix + randomCode(len);
	}
	public static String randomCode(int len){
		final int maxNum = 10;   
        int i; // 生成的随机数   
        int count = 0; // 生成的密码的长度   
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
                'x', 'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
           
       /* char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; */  
  
        StringBuffer pwd = new StringBuffer("");   
        Random r = new Random();   
        while (count < len) {   
            // 生成随机数，取绝对值，防止生成负数
  
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1   
  
            if (i >= 0 && i < str.length) {   
                pwd.append(str[i]);   
                count++;   
            }   
        }   
  
        return pwd.toString();  
	}
	/**
	 * 截断字符串
	 * 
	 * @param str 源字符串
	 * @param bytelength 字节长度
	 * @param sufix 后缀
	 * @return
	 */
	public static String truncate(String str, int bytelength, String sufix) {
		if (str == null) {
			return null;
		}
		if (bytelength <= 0) {
			return "";
		}
		try {
			if (str.getBytes("GBK").length <= bytelength) {
				return str;
			}
		} catch (Exception ex) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		while (bytelength > 0) {
			c = str.charAt(index);
			if (c < 128) {
				bytelength--;
			} else {// GBK编码汉字占2字节，其他编码的不一样，要修改下面几句
				bytelength--;
				if (bytelength < 1)// 被截断了
					break;
				bytelength--;
			}
			buff.append(c);
			index++;
		}
		buff.append(sufix);
		return buff.toString();
	}
	
	public static boolean parseBoolean(String boolStr) {
		return "true".equals(boolStr) || "1".equals(boolStr) || "yes".equalsIgnoreCase(boolStr) || "y".equalsIgnoreCase(boolStr) || "enable".equalsIgnoreCase(boolStr);
	}
	
	public static String iso2uft8(String isostr) {
		try {
			byte[] byteStr = isostr.getBytes("ISO-8859-1");
			String gbkStr = new String(byteStr, "UTF-8");
			return gbkStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String gbk2iso(String gbkstr) {
		try {
			byte[] byteStr = gbkstr.getBytes("GB2312");
			String isoStr = new String(byteStr, "ISO-8859-1");
			return isoStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String join(List<?> list, String separator) {
		return org.apache.commons.lang.StringUtils.join(list, separator);
	}
		
	public static String join(List<?> list, String separator,String warpper) {
		return join(list.toArray(),separator,warpper);
	}
	
	public static String join(Object[] list, String separator) {
		return org.apache.commons.lang.StringUtils.join(list, separator);
	}
	
	public static String join(Object[] list, String separator,String warpper) {
		List<String> warpperList = new ArrayList<String>();
		for(Object o : list){
			warpperList.add(warpper+o+warpper);
		}
		return org.apache.commons.lang.StringUtils.join(warpperList, separator);
	}
	
	public static String[] split(String src, String separator) {
		return org.apache.commons.lang.StringUtils.split(src,separator);
	}
	
	public static String split4join(String src, String srcSeparator, String separator) {
		return join(split(src,srcSeparator),separator);
		
	}
	
	public static String uuid(){
		UUID uuid = UUID.randomUUID();   
        return uuid.toString();
	}

}
