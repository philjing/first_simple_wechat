package com.phil.wechat.model.aibang;

/**
 * 公交线路查询结果的Bean
 * @author   fjing
 */
public class BusLine {
    
    //线路名称
    private String name;    
    //线路信息
    private String info;
    //沿途站点
    private String stats;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getStats() {
        return stats;
    }
    public void setStats(String stats) {
        this.stats = stats;
    }
}
