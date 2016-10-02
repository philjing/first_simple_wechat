<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>公交换乘查询</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="<%=basePath%>/jslib/amazeui2.7.2/i/favicon.png">
  <link rel="stylesheet" href="<%=basePath%>/jslib/amazeui2.7.2/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>公交换乘查询</h1>
    <h3>根据起点和终点信息查询公交换乘方案</h3>
  </div>
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <br>

    <form action="<%=basePath%>/aibangbus/transferLine" method="post" class="am-form">
      <label for="city">城市:</label>
      <input type="text" name="city" id="city">
      <br>
      <label for="start_addr">起点:</label>
      <input type="text" name="start_addr" id="start_addr">
      <br>
      <label for="end_addr">终点:</label>
      <input type="text" name="end_addr" id="end_addr">
      <br>
      <label for="location">排序方式:</label>
      <br>
                     默认是0，定义如下： 0：综合排序 1：换乘次数 2：步行距离 3：时间 4：距离 5：地铁优先
      <br>
      <br />
      <div class="am-cf">
        <input type="submit" name="" value="查询" class="am-btn am-btn-primary am-btn-sm am-fl">
      </div>
    </form>
    <hr>
    <p>© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
  </div>
</div>
</body>
</html>
