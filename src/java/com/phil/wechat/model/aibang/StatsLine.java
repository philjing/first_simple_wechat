package com.phil.wechat.model.aibang;

/**
 * 公交站点查询结果的Bean
 * @author fjing
 */
public class StatsLine {

    // 站点名称
    private String name;
    // 途径该站点的线路名称
    private String line_names;
    // 站点坐标
    private String xy;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLine_names() {
        return line_names;
    }
    public void setLine_names(String line_names) {
        this.line_names = line_names;
    }
    public String getXy() {
        return xy;
    }
    public void setXy(String xy) {
        this.xy = xy;
    }
}
