<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ソフトテク株式会社-社内管理システム </title>
<script type="text/javascript" >
function toSearchJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value=false;
	document.theForm.submit();
}
function toDownLoadDataJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value=true;
	document.theForm.submit();
}

</script>

</head>
<body>
<h2>勤怠リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="selectjyolken" action="workdetaillist" >
    <p>
      <b>対象年月:<input id="month"name="month" type="text" value="${month}"/>
         <input type="button" name="search" value="検索" onclick="toSearchJsp();" />
         <input type="hidden" id="downloadFlg"name="downloadFlg"/>
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoadDataJsp()" />
          <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>

		</p>
       </b>
    </p>

	<table border="1"class="workdetaillist-table">

		<tr>
		    <th >社員ID</th>
            <th >社員氏名</th>
            <th >対象月</th>
            <th >勤怠時間（H)</th>
            <th >定期券額（円）</th>
            <th >他の交通費（円）</th>
            <th >出張費</th>
		</tr>
		<c:forEach items="${list}" var="workdetail" varStatus="status">
			<tr <c:if test="${status.count%2==0}"> style="background-color:#80ffff"</c:if>
            	<c:if test="${status.count%2!=0}"> style="background-color:#ffff00"</c:if>>
				<td><c:out value="${workdetail.getEmployeeID()}"/></td>
				<td><c:out value="${workdetail.getEmployeeName()}"/></td>
				<td><c:out value="${workdetail.getWorkMonth()}"/></td>
				<td><c:out value="${workdetail.getWorkTime()}"/></td>
				<td><c:out value="${workdetail.getTransportExpense()}"/></td>
				<td><c:out value="${workdetail.getTransport()}"/></td>
				<td><c:out value="${workdetail.getBusinessTrip()}"/></td>

			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>