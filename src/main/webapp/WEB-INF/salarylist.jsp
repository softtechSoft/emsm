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
<h2>給料リスト</h2>
<form:form name="theForm" id="theForm" method="post" action="salarylist" >
    <p>
      <b>対象年月:<input id="month"name="month" type="text" value="${month}">
         <!--<input style="border-radius: 3px" type="submit" id="search_btn" name="search" Value="検索">
         <input style="border-radius: 3px" type="submit" name="downLoad"id="downLoad_btn" value="ダウンロード">
         -->
         <input type="button" name="search" value="検索" onclick="toSearchJsp();" />
         <input type="hidden" id="downloadFlg"name="downloadFlg"/>
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoadDataJsp()" />
       </b>
    </p>

	<table border="1"class="salrylist-table">

		<tr>
		    <th width="100">社員ID</th>
            <th width="200">社員氏名</th>
            <th width="200">対象年月</th>
            <th width="200">支払日</th>
            <th width="400">基本給(単位:円)</th>
            <th width="200">総額(単位:円)</th>
            <th width="500">備考</th>
		</tr>
		<c:forEach items="${list}" var="salarylist">
			<tr>
				<td><c:out value="${salarylist.getEmployeeID()}"/></td>
				<td><c:out value="${salarylist.getEmployeeName()}"/></td>
				<td><c:out value="${salarylist.getMonth()}"/></td>
				<td><c:out value="${salarylist.getPaymentDate()}"/></td>
				<td><c:out value="${salarylist.getBase()}"/></td>
				<td><c:out value="${salarylist.getSum()}"/></td>
				<td><c:out value="${salarylist.getRemark()}"/></td>
			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>