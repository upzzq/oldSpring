<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>过滤器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-10.js"></script>

  </head>
  
  <body>
  <h1>用过滤器来格式化数据</h1>
  	<div ng-app="myApp">
  		
  		<!-- 第二种 -->
  		<div ng-controller="firstController">
  			<!--123,456,789-->
        <p>{{123456789 | number}}</p>
        <!--12,345.679-->
        <p>{{12345.6789 | number:3}}</p>

        <!--$999,999.00-->
        <p>{{999999 | currency}}</p>
        <!--rmb999,999.00-->
        <p>{{999999 | currency:'rmb'}}</p>

        <p>
            default:{{ date }}
        </p>

        <p>
            medium: {{ date | date:'medium'}}
        </p>

        <p>
            short:{{ date | date:'short'}}
        </p>

        <p>
            fullDate:{{ date | date:'fullDate'}}
        </p>

        <p>
            longDate:{{ date | date:'longDate'}}
        </p>

        <p>
            mediumDate:{{ date | date:'mediumDate'}}
        </p>

        <p>

            shortDate:{{ date | date:'shortDate'}}
        </p>

        <p>
            mediumTime:{{ date | date:'mediumTime'}}
        </p>

        <p>
            shortTime:{{ date | date:'shortTime'}}
        </p>

        <p>
            year:
            {{date | date : 'y'}}
            {{date | date : 'yy'}}
            {{date | date : 'yyyy'}}
        </p>
        <p>
            month:
            {{date | date : 'M'}}
            {{date | date : 'MM'}}
            {{date | date : 'MMM'}}
            {{date | date : 'MMMM'}}
        </p>
        <p>
            day:
            {{date | date : 'd'}}
            Day in month {{date | date : 'dd'}}
            Day in week {{date | date : 'EEEE'}}
            {{date | date : 'EEE'}}
        </p>

        <p>
            hour:
            {{date | date : 'HH'}}
            {{date | date : 'H'}}
            {{date | date : 'hh'}}
            {{date | date : 'h'}}
        </p>

        <p>
            minute:
            {{date | date : 'mm'}}
            {{date | date : 'm'}}
        </p>

        <p>
            second:
            {{date | date : 'ss'}}
            {{date | date : 's'}}
            {{date | date : '.sss'}}
        </p>

        <p>
              	<h2>当前日期格式化</h2>:{{date | date : 'yyyy-MM-dd H:m:s'}}
        </p>

  			
  		</div>
  		
  	</div>
  </body>
</html>
