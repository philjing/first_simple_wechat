package com.phil.wechat.model.wechat;

/**
 * 微信服务器
 * @author   fjing
 */
public class WeChat {

    // 微信加密签名
    private String signature;

    // 时间戳
    private String timestamp;

    // 随机数
    private String nonce;

    // 随机字符串
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}
