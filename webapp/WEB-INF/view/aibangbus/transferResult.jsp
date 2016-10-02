<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>公交换乘查询结果</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="<%=basePath%>/jslib/amazeui2.7.2/i/favicon.png">
  <link rel="stylesheet" href="<%=basePath%>/jslib/amazeui2.7.2/css/amazeui.min.css"/>
  <style>
    @media only screen and (min-width: 641px) {
      .am-offcanvas {
        display: block;
        position: static;
        background: none;
      }

      .am-offcanvas-bar {
        position: static;
        width: auto;
        background: none;
        -webkit-transform: translate3d(0, 0, 0);
        -ms-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
      }
      .am-offcanvas-bar:after {
        content: none;
      }

    }

    @media only screen and (max-width: 640px) {
      .am-offcanvas-bar .am-nav>li>a {
        color:#ccc;
        border-radius: 0;
        border-top: 1px solid rgba(0,0,0,.3);
        box-shadow: inset 0 1px 0 rgba(255,255,255,.05)
      }

      .am-offcanvas-bar .am-nav>li>a:hover {
        background: #404040;
        color: #fff
      }

      .am-offcanvas-bar .am-nav>li.am-nav-header {
        color: #777;
        background: #404040;
        box-shadow: inset 0 1px 0 rgba(255,255,255,.05);
        text-shadow: 0 1px 0 rgba(0,0,0,.5);
        border-top: 1px solid rgba(0,0,0,.3);
        font-weight: 400;
        font-size: 75%
      }

      .am-offcanvas-bar .am-nav>li.am-active>a {
        background: #1a1a1a;
        color: #fff;
        box-shadow: inset 0 1px 3px rgba(0,0,0,.3)
      }

      .am-offcanvas-bar .am-nav>li+li {
        margin-top: 0;
      }
    }

    .my-head {
      margin-top: 40px;
      text-align: center;
    }

    .my-button {
      position: fixed;
      top: 0;
      right: 0;
      border-radius: 0;
    }
    .my-sidebar {
      padding-right: 0;
      border-right: 1px solid #eeeeee;
    }

    .my-footer {
      border-top: 1px solid #eeeeee;
      padding: 10px 0;
      margin-top: 10px;
      text-align: center;
    }
  </style>
</head>
<body>
<header class="am-g my-head">
  <div class="am-u-sm-12 am-article">
     <h1 class="am-article-title">查&nbsp;询&nbsp;结&nbsp;果&nbsp;</h1>
  </div>
</header>
<hr class="am-article-divider"/>

<div class="am-g am-g-fixed">
  <div class="am-u-md-9 am-u-md-push-3">
    <div class="am-g">
      <div class="am-u-sm-11 am-u-sm-centered">  
          <c:forEach items="${tls}" var="tl">         
	                      距离：${tl.dist}
	         <br>
	                      耗时：${tl.time}
	        <br>              
	                      起点站名：${tl.start_stat}
	        <br>              
	                      终点站名：${tl.end_stat}
	        <br>              
	                      线路名称：${tl.line_name}
	        <br>              
	                      沿途站点<br>
	        ${tl.stats}
	        <br> 
	                      行驶距离：${tl.line_dist}
	        <br>              
	                      步行距离：${tl.foot_dist}
            <br>
            <hr class="am-article-divider"/>
          </c:forEach>
      </div>
    </div>
  </div>
</div>

<footer class="my-footer">
  <p>查询结果来源于爱帮公交提供的数据，仅供参考<br><small>© Copyright By Phil Jing</small></p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="<%=basePath%>/jslib/amazeui2.7.2/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>/jslib/amazeui2.7.2/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=basePath%>/jslib/amazeui2.7.2/js/amazeui.min.js"></script>
</body>
</html>
