package com.phil.wechat.model.aibang;

/** 
 * 公交换乘查询结果的Bean
 * @author   fjing
 */
public class TransferLine {
    // 总距离 单位：米
    private String dist;
    // 估计耗费时间 单位：分钟
    private String time;
    // 起点站名
    private String start_stat;
    // 终点站名
    private String end_stat;
    // 线路名称
    private String line_name;
    // 沿途站点
    private String stats;
    // 行驶距离 单位：米
    private String line_dist;
    // 步行距离 单位：米
    private String foot_dist;
    public String getDist() {
        return dist;
    }
    public void setDist(String dist) {
        this.dist = dist;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getStart_stat() {
        return start_stat;
    }
    public void setStart_stat(String start_stat) {
        this.start_stat = start_stat;
    }
    public String getEnd_stat() {
        return end_stat;
    }
    public void setEnd_stat(String end_stat) {
        this.end_stat = end_stat;
    }
    public String getLine_name() {
        return line_name;
    }
    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }
    public String getStats() {
        return stats;
    }
    public void setStats(String stats) {
        this.stats = stats;
    }
    public String getLine_dist() {
        return line_dist;
    }
    public void setLine_dist(String line_dist) {
        this.line_dist = line_dist;
    }
    public String getFoot_dist() {
        return foot_dist;
    }
    public void setFoot_dist(String foot_dist) {
        this.foot_dist = foot_dist;
    }
}
