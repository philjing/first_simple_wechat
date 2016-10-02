package com.phil.common.util;

import java.io.UnsupportedEncodingException;

public class PhilUtil {
	
    /**
     * UTF 8处理
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();
        }  
        return result;  
    }
}
