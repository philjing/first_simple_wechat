package com.phil.wechat.service.face;

import java.util.List;

import com.phil.wechat.model.face.Face;

/**
 * 人脸检测功能
 * @author fjing
 *
 */
public interface FaceService {
	
    /** 
     * 调用Face++ API实现人脸检测 
     *  
     * @param picUrl 待检测图片的访问地址 
     */ 
    public List<Face> faceDetect(String picUrl);
    
    /** 
     * 性别转换（英文->中文） 
     *  
     * @param gender 
     */  
    public String genderConvert(String gender) ;
    
    /** 
     * 人种转换（英文->中文） 
     *  
     * @param race 
     */  
    public String raceConvert(String race) ;
    
    /** 
     * 根据人脸识别结果组装消息 
     *  
     * @param faceList 人脸列表 
     */  
    public String makeMessage(List<Face> faceList);
    
    /** 
     * 提供给外部调用的人脸检测方法 
     * @param picUrl 待检测图片的访问地址 
     */ 
    public String detect(String picUrl);
    
    /**
     * 人脸检测帮助菜单
     */
    public String getFaceUsage();
}

